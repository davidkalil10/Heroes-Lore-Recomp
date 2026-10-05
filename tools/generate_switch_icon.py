#!/usr/bin/env python3
"""
Gera o ícone oficial do Nintendo Switch para o Homebrew Menu (256x256 JPEG).
"""
import os
from PIL import Image

def generate_switch_icon():
    src_path = "assets/logo_512.png"
    if not os.path.exists(src_path):
        print(f"Erro: {src_path} não encontrado!")
        return

    os.makedirs("switch", exist_ok=True)
    out_path = "switch/icon.jpg"

    # Abre a imagem original
    img = Image.open(src_path).convert("RGBA")

    # Cria fundo preto/escuro para preencher caso haja transparência
    bg = Image.new("RGB", (512, 512), (18, 18, 22))
    bg.paste(img, (0, 0), img)

    # Redimensiona para 256x256 com filtro Lanczos de alta qualidade
    icon = bg.resize((256, 256), Image.Resampling.LANCZOS)

    # Salva como JPEG com qualidade máxima
    icon.save(out_path, "JPEG", quality=95, optimize=True)
    print(f"[OK] Ícone do Switch gerado com sucesso em: {out_path} (256x256)")

if __name__ == "__main__":
    generate_switch_icon()
