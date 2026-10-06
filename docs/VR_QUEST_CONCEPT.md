# 🥽 Conceito Técnico: Heroes Lore VR / MR para Meta Quest (OpenXR + Voxel 3D)

Este documento registra o conceito, viabilidade e arquitetura planejada para uma futura versão em Realidade Virtual (VR) e Realidade Mista (MR) de **Heroes Lore: Wind of Soltia** no **Meta Quest (2, 3, 3S, Pro)**.

---

## 1. Visão Geral e Inspiração

A inspiração para este port baseia-se em projetos de grande sucesso na comunidade de emulação e recompilação, como:
- **Pokemon Yellow 3D / VoxelMod:** Conversão de clássicos 2D em mundos 3D cúbicos/voxelizados totalmente exploráveis em VR.
- **3DNes VR:** Extrusão de camadas 2D em profundidade estereoscópica real com rastreamento 6DOF.
- **Moss & Demeo (VR/MR):** Apresentação do mundo de jogo como um **diorama vivo / maquete tridimensional interativa** flutuando sobre uma mesa física no espaço do jogador.

Como o nosso projeto é uma **recompilação nativa em C++17** (com acesso direto à memória de objetos, posições de entidades e matrizes de tiles), essa transformação é infinitamente mais limpa e precisa do que seria em um emulador tradicional.

---

## 2. Arquitetura do Meta Quest (Meta Horizon OS)

O sistema operacional dos headsets Meta Quest é baseado em **Android ARM64**:
- O nosso binário atual já compila nativamente para **Android ARM64** via NDK (`arm64-v8a`).
- O ecossistema Meta suporta oficialmente a API aberta e padronizada **OpenXR**, com renderização via **OpenGL ES 3.2** ou **Vulkan**.
- Uma nova camada de plataforma (`src/platform/platform_openxr.cpp`) substituirá a janela 2D da SDL por uma sessão de renderização estereoscópica (olho esquerdo + olho direito) com rastreamento de cabeça (Head Tracking 6DOF).

---

## 3. Modos de Visualização Planejados

### 🏛️ Modo A: Diorama de Mesa Viva em Realidade Mista (Passthrough Colorido)
- **Experiência:** O mapa clássico de Soltia é projetado como uma maquete 3D viva flutuando na mesa de centro ou escrivaninha da sua sala (usando as câmeras coloridas de Passthrough do Quest 3 / 3S).
- **Interação 6DOF:** O jogador pode se levantar, caminhar ao redor da mesa, inclinar a cabeça para inspecionar os interiores das casas ou ver os monstros escondidos atrás de árvores e montanhas.
- **Controle:** O personagem (Ronin / Aram) continua sendo controlado suavemente pelos analógicos do controle Quest Touch, respondendo com movimentação 360°.

### ⚔️ Modo B: Imersão 360° em Terceira/Primeira Pessoa
- **Experiência:** A câmera do jogador é posicionada no nível do solo ou logo atrás dos ombros do herói.
- **Mundo Voxelizado:** As paredes das masmorras e cidades erguem-se em escala humana tridimensional.
- **Combate com Controles Touch:** O jogador pode desferir ataques usando os gatilhos dos controles ou através de movimentos físicos com os braços, acompanhados de vibração háptica nos controles.

---

## 4. Engenharia de Conversão 2D $\rightarrow$ 3D (Voxelização de Mapas)

No formato original de *Heroes Lore*, os mapas são compostos por matrizes de tiles de $16 \times 16$ pixels (armazenados em `reference/extracted/` nos arquivos de script de evento e mapas):

1. **Camada de Chão / Terreno ($Z = 0.0$):**
   - Grama, terra, pisos de pedra e areia são projetados no plano base.
2. **Camada de Água e Relevos Negativos ($Z = -0.2$):**
   - Rios e lagos recebem profundidade ligeiramente rebaixada com material translúcido e animação de reflexo/ondulação.
3. **Camada de Obstáculos e Paredes ($Z = 1.0 \text{ a } 2.5$):**
   - Troncos de árvores, paredes de casas, rochas e montanhas são extrudados verticalmente em colunas tridimensionais ou malhas de voxels com textura nas laterais.
4. **Camada Superior / Copas de Árvores e Telhados ($Z = 3.0$):**
   - Coberturas e telhados ficam suspensos no topo, podendo ter transparência adaptativa quando o jogador ou Ronin entram embaixo deles.
5. **Entidades Tridimensionais (Heróis, Monstros, NPCs):**
   - Sprites 2D convertidos em figuras volumétricas em estilo *billboard* avançado ou malhas de voxels 3D (estilo *Crossy Road / 3DNes*).

---

## 5. Implementação Técnica da Camada de Plataforma

```
src/platform/
├── platform.h                 # Interface abstrata comum
├── platform_sdl.cpp           # Backend para PC (Windows/Linux) e Switch
├── platform_openxr.cpp        # Novo backend dedicado para Meta Quest
└── voxel_mesher.cpp           # Construtor de malhas tridimensionais a partir dos tiles 2D
```

### Principais Componentes:
- **`xrCreateSession` & `xrWaitFrame`:** Sincronização do loop de jogo a 72Hz / 90Hz / 120Hz nativos do Quest, eliminando motion sickness.
- **`xrLocateViews`:** Obtenção em tempo real da posição e orientação exata da cabeça do jogador no espaço tridimensional.
- **`Mapeamento de Controles Touch`:**
  - Analógico Esquerdo: Movimentação (WASD / 2, 4, 6, 8).
  - Analógico Direito: Rotação suave da câmera da mesa ou snap-turn.
  - Botão <kbd>A</kbd>: Ataque / Confirmação ('5').
  - Botão <kbd>B</kbd>: Status / Cancelar (RSK).
  - Botão <kbd>X</kbd> / <kbd>Y</kbd>: Magias dos Guardiões ('1' / '3').
  - Gatilho <kbd>R</kbd> (Trigger): Usar Poção rápida ('9').
  - Grip Traseiro: Agarrar e rotacionar / redimensionar a mesa do diorama livremente.

---

## 6. Viabilidade e Pré-requisitos

- **Complexidade:** Média-Alta (exige criação da rotina de extrusão de malha e integração com a API OpenXR).
- **Base Pronta:** A lógica J2ME, interpretador de bytecodes, áudio, salvamento e física já rodam nativamente em ARM64. O projeto precisa apenas de uma camada de renderização alternativa.
- **Status:** Registrado oficialmente no Roadmap do projeto para futuras fases de expansão inovadora.

---

## 7. Repositórios e Projetos de Referência para Consulta Técnica

Caso seja necessário analisar a arquitetura de conversão voxelizada e recompilação em fases futuras, consultar os seguintes repositórios:

1. **[polymathiclabs/pokemon-gen1-voxel-vr](https://github.com/polymathiclabs/pokemon-gen1-voxel-vr):**
   - Implementação de referência em Realidade Virtual imersiva convertendo tilemaps e sprites de primeira geração em voxels com OpenXR e controles 6DOF.
2. **[polymathiclabs/DramaticShapeVoxelMod](https://github.com/polymathiclabs/DramaticShapeVoxelMod):**
   - Motor central de voxelização (*VoxelMod*), responsável pela extrusão geométrica das camadas 2D em blocos 3D com malhas eficientes.
3. **[bryanthaboi/gen1recomp](https://github.com/bryanthaboi/gen1recomp):**
   - Recompilação nativa estática em C de Pokemon Gen 1 (excelente espelho metodológico para a nossa recompilação em C++ de Heroes Lore).
4. **[blackwing182/DramaticShapeVoxelMod-test](https://github.com/blackwing182/DramaticShapeVoxelMod-test):**
   - Fork de testes e instrumentação de renderização do DramaticShapeVoxelMod.
5. **[linkfy/DramaticShapeVoxelModBackup](https://github.com/linkfy/DramaticShapeVoxelModBackup):**
   - Snapshot e documentação de suporte do motor VoxelMod.
6. **[scottcandy34/DramaticShapeVoxelMod-latest](https://github.com/scottcandy34/DramaticShapeVoxelMod-latest):**
   - Versão revisada com patches e atualizações da comunidade do DramaticShapeVoxelMod.

