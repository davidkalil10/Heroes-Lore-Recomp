# tools/generate_bezel_textures.py
# Gera texturas de molduras temáticas (Bezels) para widescreen (16:9 / 16:10)
# - bezel_soltia: Moldura temática inspirada nas colunas rúnicas de Soltia, relevos arcanos e brasão
# - bezel_slate: Moldura discreta e elegante em ardósia vulcânica / metal escuro escovado

import os
import math
import struct
from PIL import Image, ImageDraw, ImageFont, ImageFilter

FONT_TITLE = "C:/Windows/Fonts/trajan.ttf"
if not os.path.exists(FONT_TITLE):
    FONT_TITLE = "C:/Windows/Fonts/cinzel.ttf"
if not os.path.exists(FONT_TITLE):
    FONT_TITLE = "C:/Windows/Fonts/georgiab.ttf"
if not os.path.exists(FONT_TITLE):
    FONT_TITLE = "C:/Windows/Fonts/segoeuib.ttf"

FONT_RUNE = "C:/Windows/Fonts/segoeui.ttf"

def save_rgba(img, out_path):
    os.makedirs(os.path.dirname(out_path), exist_ok=True)
    w, h = img.size
    raw_data = img.tobytes("raw", "RGBA")
    with open(out_path, "wb") as f:
        # Header: uint32 width, uint32 height
        f.write(struct.pack("<II", w, h))
        f.write(raw_data)
    png_path = os.path.splitext(out_path)[0] + ".png"
    img.save(png_path)
    print(f"Salvo: {out_path} ({w}x{h}) e {png_path}")

def draw_beveled_rect(draw, bbox, fill, light, dark, width=2):
    x0, y0, x1, y1 = bbox
    draw.rectangle(bbox, fill=fill)
    for i in range(width):
        # Top e Left = Luz
        draw.line([(x0 + i, y0 + i), (x1 - i, y0 + i)], fill=light)
        draw.line([(x0 + i, y0 + i), (x0 + i, y1 - i)], fill=light)
        # Bottom e Right = Sombra
        draw.line([(x0 + i, y1 - i), (x1 - i, y1 - i)], fill=dark)
        draw.line([(x1 - i, y0 + i), (x1 - i, y1 - i)], fill=dark)

def create_bezel_soltia(width=1920, height=1080):
    # Proporção central 3:4 do jogo em 1080p:
    # Altura do jogo = 1080. Largura = 1080 * (240 / 320) = 810.
    # game_x0 = (1920 - 810) // 2 = 555
    # game_x1 = 555 + 810 = 1365
    game_w = int(height * 0.75)
    game_x0 = (width - game_w) // 2
    game_x1 = game_x0 + game_w

    img = Image.new('RGBA', (width, height), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)

    # 1. Base em ardósia mística de Soltia para as laterais
    # Fundo texturizado com blocos de pedra
    for panel_x0, panel_x1, is_right in [(0, game_x0, False), (game_x1, width, True)]:
        panel_w = panel_x1 - panel_x0
        # Gradiente suave do painel (mais escuro na borda externa, iluminado no pilar)
        for y in range(height):
            # Gradiente vertical sutil
            vy = math.sin((y / height) * math.pi)
            base_r = int(14 + vy * 5)
            base_g = int(16 + vy * 6)
            base_b = int(24 + vy * 10)
            draw.line([(panel_x0, y), (panel_x1, y)], fill=(base_r, base_g, base_b, 255))

        # Blocos de pedra rúnica (pedra talhada ancestral)
        block_h = 54
        block_w = 111
        for row in range(height // block_h + 1):
            by = row * block_h
            offset = (row % 2) * (block_w // 2)
            for col in range((panel_w // block_w) + 2):
                bx = panel_x0 + col * block_w - offset
                if bx + block_w < panel_x0 or bx > panel_x1:
                    continue
                # Ajusta limites para dentro do painel
                cx0 = max(panel_x0, bx)
                cx1 = min(panel_x1, bx + block_w)
                cy0 = max(0, by)
                cy1 = min(height, by + block_h)
                if cx1 > cx0 and cy1 > cy0:
                    draw_beveled_rect(draw, [cx0, cy0, cx1, cy1],
                                      fill=(18, 22, 32, 255),
                                      light=(38, 48, 68, 120),
                                      dark=(8, 10, 15, 200),
                                      width=1)

        # Pilar Místico Central dentro de cada painel
        pillar_w = int(panel_w * 0.58)
        pillar_x0 = panel_x0 + (panel_w - pillar_w) // 2
        pillar_x1 = pillar_x0 + pillar_w

        # Sombra do pilar sobre a parede
        draw.rectangle([pillar_x0 - 15, 0, pillar_x1 + 15, height], fill=(6, 8, 12, 140))

        # Corpo do Pilar (Mármore Escuro / Basalto Estilizado)
        draw_beveled_rect(draw, [pillar_x0, 0, pillar_x1, height],
                          fill=(24, 28, 40, 255),
                          light=(65, 80, 110, 255),
                          dark=(10, 12, 18, 255),
                          width=4)

        # Canaletas verticais do pilar (estilo clássico nórdico)
        flute_count = 5
        flute_w = (pillar_w - 40) // flute_count
        for f in range(flute_count):
            fx0 = pillar_x0 + 20 + f * flute_w
            fx1 = fx0 + flute_w - 6
            draw_beveled_rect(draw, [fx0, 30, fx1, height - 30],
                              fill=(16, 19, 28, 255),
                              light=(10, 12, 16, 255),
                              dark=(50, 62, 85, 200),
                              width=2)

        # Capitéis ornamentados no topo e base do pilar
        for cap_y0, cap_y1 in [(0, 60), (height - 60, height)]:
            draw_beveled_rect(draw, [pillar_x0 - 8, cap_y0, pillar_x1 + 8, cap_y1],
                              fill=(35, 42, 58, 255),
                              light=(95, 115, 155, 255),
                              dark=(10, 12, 18, 255),
                              width=3)
            # Relevo dourado de bronze no capitel
            draw.rectangle([pillar_x0, cap_y0 + 20, pillar_x1, cap_y0 + 26],
                           fill=(195, 155, 60, 240))
            draw.line([(pillar_x0, cap_y0 + 20), (pillar_x1, cap_y0 + 20)], fill=(255, 225, 120, 255))
            draw.line([(pillar_x0, cap_y0 + 26), (pillar_x1, cap_y0 + 26)], fill=(110, 80, 25, 255))

        # Friso Dourado Ornamental nos painéis
        border_x = panel_x1 - 6 if not is_right else panel_x0
        draw.rectangle([border_x, 0, border_x + 6, height], fill=(160, 130, 50, 255))
        draw.line([(border_x, 0), (border_x, height)], fill=(225, 195, 95, 255))
        draw.line([(border_x + 5, 0), (border_x + 5, height)], fill=(75, 55, 15, 255))

    # 2. Brasão / Relevo Místico e Título
    # Painel Esquerdo: Medalhão Heráldico de Soltia com Runas
    left_cx = game_x0 // 2
    r_medallion = 72
    cy_medallion = height // 2 - 120

    # Halo azul-arcano brilhante atrás do medalhão
    for r in range(r_medallion + 40, r_medallion, -4):
        alpha = int(90 * (1.0 - (r - r_medallion) / 40.0))
        draw.ellipse([left_cx - r, cy_medallion - r, left_cx + r, cy_medallion + r],
                     fill=(0, 180, 255, alpha))

    # Medalhão de Bronze Nórdico
    draw.ellipse([left_cx - r_medallion, cy_medallion - r_medallion,
                  left_cx + r_medallion, cy_medallion + r_medallion],
                 fill=(28, 32, 45, 255), outline=(210, 170, 70, 255), width=4)
    draw.ellipse([left_cx - r_medallion + 8, cy_medallion - r_medallion + 8,
                  left_cx + r_medallion - 8, cy_medallion + r_medallion - 8],
                 outline=(100, 130, 180, 200), width=2)

    # Espada Mística Alada no centro do medalhão
    # Lâmina
    draw.polygon([(left_cx, cy_medallion - 45),
                  (left_cx - 6, cy_medallion + 15),
                  (left_cx, cy_medallion + 20),
                  (left_cx + 6, cy_medallion + 15)], fill=(220, 240, 255, 255))
    draw.line([(left_cx, cy_medallion - 45), (left_cx, cy_medallion + 20)], fill=(0, 220, 255, 255), width=2)
    # Guarda da Espada
    draw.rectangle([left_cx - 18, cy_medallion + 15, left_cx + 18, cy_medallion + 20], fill=(230, 185, 75, 255))
    # Cabo e Pomo
    draw.rectangle([left_cx - 3, cy_medallion + 20, left_cx + 3, cy_medallion + 34], fill=(130, 95, 35, 255))
    draw.ellipse([left_cx - 5, cy_medallion + 34, left_cx + 5, cy_medallion + 44], fill=(230, 185, 75, 255))

    # Asas estilizadas do brasão
    draw.polygon([(left_cx - 16, cy_medallion + 10), (left_cx - 42, cy_medallion - 12),
                  (left_cx - 28, cy_medallion + 8), (left_cx - 45, cy_medallion + 2),
                  (left_cx - 20, cy_medallion + 20)], fill=(0, 200, 255, 190))
    draw.polygon([(left_cx + 16, cy_medallion + 10), (left_cx + 42, cy_medallion - 12),
                  (left_cx + 28, cy_medallion + 8), (left_cx + 45, cy_medallion + 2),
                  (left_cx + 20, cy_medallion + 20)], fill=(0, 200, 255, 190))

    # Função para desenhar runas nórdicas procedurais entalhadas com brilho
    def draw_rune(rx, ry, r_type, sz=18):
        # Brilho de halo azul-ciano
        for off in [(-2, 0), (2, 0), (0, -2), (0, 2), (-1, -1), (1, 1)]:
            ox, oy = rx + off[0], ry + off[1]
            c_glow = (0, 180, 255, 100)
            if r_type == 'algiz': # ᛉ
                draw.line([(ox, oy - sz), (ox, oy + sz)], fill=c_glow, width=4)
                draw.line([(ox, oy - sz//3), (ox - sz, oy - sz)], fill=c_glow, width=4)
                draw.line([(ox, oy - sz//3), (ox + sz, oy - sz)], fill=c_glow, width=4)
            elif r_type == 'tiwaz': # ᛏ
                draw.line([(ox, oy - sz), (ox, oy + sz)], fill=c_glow, width=4)
                draw.line([(ox, oy - sz), (ox - sz, oy - sz//2)], fill=c_glow, width=4)
                draw.line([(ox, oy - sz), (ox + sz, oy - sz//2)], fill=c_glow, width=4)
            elif r_type == 'fehu': # ᚠ
                draw.line([(ox - sz//2, oy - sz), (ox - sz//2, oy + sz)], fill=c_glow, width=4)
                draw.line([(ox - sz//2, oy - sz//2), (ox + sz//2, oy - sz)], fill=c_glow, width=4)
                draw.line([(ox - sz//2, oy), (ox + sz//2, oy - sz//2)], fill=c_glow, width=4)
            elif r_type == 'sowilo': # ᛋ
                draw.line([(ox + sz//2, oy - sz), (ox - sz//2, oy - sz//3)], fill=c_glow, width=4)
                draw.line([(ox - sz//2, oy - sz//3), (ox + sz//2, oy + sz//3)], fill=c_glow, width=4)
                draw.line([(ox + sz//2, oy + sz//3), (ox - sz//2, oy + sz)], fill=c_glow, width=4)
            elif r_type == 'gebo': # ᚷ
                draw.line([(ox - sz, oy - sz), (ox + sz, oy + sz)], fill=c_glow, width=4)
                draw.line([(ox + sz, oy - sz), (ox - sz, oy + sz)], fill=c_glow, width=4)
            elif r_type == 'othala': # ᛟ
                draw.line([(ox, oy - sz), (ox - sz, oy)], fill=c_glow, width=4)
                draw.line([(ox, oy - sz), (ox + sz, oy)], fill=c_glow, width=4)
                draw.line([(ox - sz, oy), (ox + sz, oy + sz)], fill=c_glow, width=4)
                draw.line([(ox + sz, oy), (ox - sz, oy + sz)], fill=c_glow, width=4)

        # Traço principal branco brilhante com azul arcano
        c_core = (220, 245, 255, 250)
        if r_type == 'algiz':
            draw.line([(rx, ry - sz), (rx, ry + sz)], fill=c_core, width=2)
            draw.line([(rx, ry - sz//3), (rx - sz, ry - sz)], fill=c_core, width=2)
            draw.line([(rx, ry - sz//3), (rx + sz, ry - sz)], fill=c_core, width=2)
        elif r_type == 'tiwaz':
            draw.line([(rx, ry - sz), (rx, ry + sz)], fill=c_core, width=2)
            draw.line([(rx, ry - sz), (rx - sz, ry - sz//2)], fill=c_core, width=2)
            draw.line([(rx, ry - sz), (rx + sz, ry - sz//2)], fill=c_core, width=2)
        elif r_type == 'fehu':
            draw.line([(rx - sz//2, ry - sz), (rx - sz//2, ry + sz)], fill=c_core, width=2)
            draw.line([(rx - sz//2, ry - sz//2), (rx + sz//2, ry - sz)], fill=c_core, width=2)
            draw.line([(rx - sz//2, ry), (rx + sz//2, ry - sz//2)], fill=c_core, width=2)
        elif r_type == 'sowilo':
            draw.line([(rx + sz//2, ry - sz), (rx - sz//2, ry - sz//3)], fill=c_core, width=2)
            draw.line([(rx - sz//2, ry - sz//3), (rx + sz//2, ry + sz//3)], fill=c_core, width=2)
            draw.line([(rx + sz//2, ry + sz//3), (rx - sz//2, ry + sz)], fill=c_core, width=2)
        elif r_type == 'gebo':
            draw.line([(rx - sz, ry - sz), (rx + sz, ry + sz)], fill=c_core, width=2)
            draw.line([(rx + sz, ry - sz), (rx - sz, ry + sz)], fill=c_core, width=2)
        elif r_type == 'othala':
            draw.line([(rx, ry - sz), (rx - sz, ry)], fill=c_core, width=2)
            draw.line([(rx, ry - sz), (rx + sz, ry)], fill=c_core, width=2)
            draw.line([(rx - sz, ry), (rx + sz, ry + sz)], fill=c_core, width=2)
            draw.line([(rx + sz, ry), (rx - sz, ry + sz)], fill=c_core, width=2)

    # Fileira de Runas Arcanas na Coluna Esquerda
    left_rune_types = ['algiz', 'tiwaz', 'fehu', 'sowilo', 'gebo', 'othala']
    start_ry = cy_medallion + 110
    step_ry = 52
    for idx, r_type in enumerate(left_rune_types):
        draw_rune(left_cx, start_ry + idx * step_ry, r_type, sz=15)

    # Inscrição mística SOLTIA na coluna esquerda
    try:
        font_soltia = ImageFont.truetype(FONT_TITLE, 20)
    except:
        font_soltia = ImageFont.load_default()

    txt_s = "• SOLTIA •"
    bbox_s = draw.textbbox((0, 0), txt_s, font=font_soltia)
    ws = bbox_s[2] - bbox_s[0]
    draw.text((left_cx - ws // 2, start_ry + len(left_rune_types) * step_ry + 10), txt_s, font=font_soltia, fill=(230, 195, 95, 230))

    # Painel Direito: Logotipo Gravado Oficial em Ouro e Prata
    right_cx = game_x1 + (width - game_x1) // 2
    try:
        font_hl = ImageFont.truetype(FONT_TITLE, 34)
        font_wos = ImageFont.truetype(FONT_TITLE, 22)
        font_tag = ImageFont.truetype(FONT_TITLE, 15)
        font_credit = ImageFont.truetype(FONT_TITLE, 14)
    except:
        font_hl = ImageFont.load_default()
        font_wos = font_hl
        font_tag = font_hl
        font_credit = font_hl

    logo_y = cy_medallion - 40
    # Placa entalhada para o título
    plaque_w = 340
    plaque_h = 160
    draw_beveled_rect(draw, [right_cx - plaque_w // 2, logo_y, right_cx + plaque_w // 2, logo_y + plaque_h],
                      fill=(18, 22, 32, 255),
                      light=(85, 105, 140, 240),
                      dark=(8, 10, 15, 255),
                      width=3)
    # Filete dourado interno
    draw.rectangle([right_cx - plaque_w // 2 + 6, logo_y + 6, right_cx + plaque_w // 2 - 6, logo_y + plaque_h - 6],
                   outline=(180, 140, 50, 200), width=2)

    # Texto HEROES LORE
    txt_hl = "HEROES LORE"
    bbox_hl = draw.textbbox((0, 0), txt_hl, font=font_hl)
    w_hl = bbox_hl[2] - bbox_hl[0]
    draw.text((right_cx - w_hl // 2 + 2, logo_y + 24), txt_hl, font=font_hl, fill=(0, 0, 0, 255))
    draw.text((right_cx - w_hl // 2, logo_y + 22), txt_hl, font=font_hl, fill=(245, 205, 100, 255))

    # Texto WIND OF SOLTIA
    txt_wos = "WIND OF SOLTIA"
    bbox_wos = draw.textbbox((0, 0), txt_wos, font=font_wos)
    w_wos = bbox_wos[2] - bbox_wos[0]
    draw.text((right_cx - w_wos // 2 + 1, logo_y + 73), txt_wos, font=font_wos, fill=(0, 0, 0, 240))
    draw.text((right_cx - w_wos // 2, logo_y + 72), txt_wos, font=font_wos, fill=(215, 235, 255, 240))

    # Divisor dourado com losango
    div_y = logo_y + 112
    draw.line([(right_cx - 100, div_y), (right_cx + 100, div_y)], fill=(160, 130, 50, 220), width=2)
    draw.polygon([(right_cx, div_y - 6), (right_cx + 6, div_y), (right_cx, div_y + 6), (right_cx - 6, div_y)],
                 fill=(255, 215, 90, 255))

    # Sub-rótulo Recompilação Nativa
    txt_sub = "RECOMPILACAO NATIVA C++17"
    bbox_sub = draw.textbbox((0, 0), txt_sub, font=font_tag)
    w_sub = bbox_sub[2] - bbox_sub[0]
    draw.text((right_cx - w_sub // 2, logo_y + 126), txt_sub, font=font_tag, fill=(140, 170, 210, 230))

    # Detalhes informativos e créditos no painel direito
    right_labels = [
        "DAVID KALIL BRAGA (2026)",
        "EA MOBILE / OPEN MIND TEAM",
        "ASPECT RATIO: 3:4 ORIGINAL",
        "TAXA: 30 FPS / 60 FPS [F6]",
        "MOLDURA: TEMATICA [F5]"
    ]
    r_start_y = logo_y + plaque_h + 30
    for idx, lbl in enumerate(right_labels):
        bbox_l = draw.textbbox((0, 0), lbl, font=font_credit)
        wl = bbox_l[2] - bbox_l[0]
        # Borda sutil nos itens de comando
        if "[" in lbl:
            draw.text((right_cx - wl // 2, r_start_y + idx * 36), lbl, font=font_credit, fill=(0, 210, 255, 220))
        elif "DAVID" in lbl:
            draw.text((right_cx - wl // 2, r_start_y + idx * 36), lbl, font=font_credit, fill=(245, 210, 110, 240))
        else:
            draw.text((right_cx - wl // 2, r_start_y + idx * 36), lbl, font=font_credit, fill=(160, 185, 215, 210))

    # 3. Chanfro de Profundidade e Sombra Suave na junção com o jogo (Inner Shadow)
    # Borda esquerda da tela do jogo (lado esquerdo)
    shadow_w = 28
    for i in range(shadow_w):
        alpha = int(220 * (1.0 - (i / shadow_w) ** 0.8))
        draw.line([(game_x0 - i, 0), (game_x0 - i, height)], fill=(0, 0, 0, alpha))
        draw.line([(game_x1 + i, 0), (game_x1 + i, height)], fill=(0, 0, 0, alpha))

    # Borda dourada brilhante de separação precisa
    draw.line([(game_x0 - 1, 0), (game_x0 - 1, height)], fill=(200, 160, 70, 255), width=2)
    draw.line([(game_x1, 0), (game_x1, height)], fill=(200, 160, 70, 255), width=2)

    return img

def create_bezel_slate(width=1920, height=1080):
    game_w = int(height * 0.75)
    game_x0 = (width - game_w) // 2
    game_x1 = game_x0 + game_w

    img = Image.new('RGBA', (width, height), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)

    # Ardósia vulcânica escura com textura sutil e micro-chanfros
    for panel_x0, panel_x1 in [(0, game_x0), (game_x1, width)]:
        for y in range(height):
            # Gradiente vertical suave de metal escovado
            t = math.sin((y / height) * math.pi)
            v = int(14 + t * 6)
            draw.line([(panel_x0, y), (panel_x1, y)], fill=(v, v + 2, v + 4, 255))

        # Faixas de metal escovado verticais
        p_w = panel_x1 - panel_x0
        step = 60
        for x in range(panel_x0, panel_x1, step):
            draw_beveled_rect(draw, [x, 0, min(panel_x1, x + step), height],
                              fill=(16, 18, 22, 255),
                              light=(32, 36, 44, 180),
                              dark=(6, 8, 10, 220),
                              width=1)

    # Chanfro e sombra na borda do jogo
    shadow_w = 24
    for i in range(shadow_w):
        alpha = int(240 * (1.0 - i / shadow_w))
        draw.line([(game_x0 - i, 0), (game_x0 - i, height)], fill=(0, 0, 0, alpha))
        draw.line([(game_x1 + i, 0), (game_x1 + i, height)], fill=(0, 0, 0, alpha))

    # Linha de destaque em cinza titânio
    draw.line([(game_x0 - 1, 0), (game_x0 - 1, height)], fill=(70, 80, 95, 255), width=2)
    draw.line([(game_x1, 0), (game_x1, height)], fill=(70, 80, 95, 255), width=2)

    return img

def create_font_osd():
    # 96 caracteres ASCII: 32 (espaço) a 127
    cell_w, cell_h = 22, 36
    cols, rows = 16, 6
    img = Image.new('RGBA', (cols * cell_w, rows * cell_h), (0, 0, 0, 0))

    font_path = "C:/Windows/Fonts/segoeuib.ttf"
    if not os.path.exists(font_path):
        font_path = "C:/Windows/Fonts/arialbd.ttf"
    try:
        font = ImageFont.truetype(font_path, 22)
    except:
        font = ImageFont.load_default()

    for idx in range(96):
        ascii_code = 32 + idx
        char = chr(ascii_code)
        col = idx % cols
        row = idx // cols

        cell_img = Image.new('RGBA', (cell_w, cell_h), (0, 0, 0, 0))
        d_cell = ImageDraw.Draw(cell_img)

        bbox = d_cell.textbbox((0, 0), char, font=font)
        tw = bbox[2] - bbox[0]
        th = bbox[3] - bbox[1]
        tx = (cell_w - tw) // 2
        ty = (cell_h - th) // 2 - 1

        # Sombra sutil escura
        for ox in [-1, 0, 1]:
            for oy in [-1, 0, 1]:
                if ox != 0 or oy != 0:
                    d_cell.text((tx + ox, ty + oy), char, font=font, fill=(5, 8, 14, 230))
        # Caractere branco brilhante
        d_cell.text((tx, ty), char, font=font, fill=(255, 255, 255, 255))

        img.paste(cell_img, (col * cell_w, row * cell_h))

    return img

def main():
    out_dir = "assets/ui"
    os.makedirs(out_dir, exist_ok=True)

    print("Gerando Bezel Soltia (1920x1080)...")
    img_soltia = create_bezel_soltia(1920, 1080)
    save_rgba(img_soltia, os.path.join(out_dir, "bezel_soltia.rgba"))

    print("Gerando Bezel Slate (1920x1080)...")
    img_slate = create_bezel_slate(1920, 1080)
    save_rgba(img_slate, os.path.join(out_dir, "bezel_slate.rgba"))

    print("Gerando Fonte OSD (512x288)...")
    img_font = create_font_osd()
    save_rgba(img_font, os.path.join(out_dir, "font_osd.rgba"))

    print("Concluído!")

if __name__ == "__main__":
    main()

