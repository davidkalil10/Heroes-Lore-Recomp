#!/usr/bin/env python3
"""
Extrai assets do JAR e converte para formatos modernos
"""

import zipfile
import json
import struct
import shutil
from pathlib import Path
from typing import Dict, List, Any
import os

class HeroesLoreExtractor:
    def __init__(self, jar_path: str, output_dir: str):
        self.jar_path = jar_path
        self.output_dir = Path(output_dir)
        self.output_dir.mkdir(exist_ok=True, parents=True)
    
    def extract_images(self):
        """Extrai todas as imagens PNG"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            png_files = [f for f in jar.namelist() if f.endswith('.png')]
            for png in png_files:
                data = jar.read(png)
                out_path = self.output_dir / 'sprites' / Path(png).name
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(data)
        print(f"✓ Extraídas {len(png_files)} imagens")
    
    def extract_audio(self):
        """Extrai áudio (MIDI e WAV)"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            audio_files = [f for f in jar.namelist() 
                          if f.endswith('.mid') or f.endswith('.wav')]
            for audio in audio_files:
                data = jar.read(audio)
                out_path = self.output_dir / 'audio' / Path(audio).name
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(data)
        print(f"✓ Extraído áudio: {len(audio_files)} arquivos")
    
    def extract_game_data(self):
        """Extrai dados do jogo (maps, npcs, items, etc)"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            # Extrair arquivos de dados
            data_files = [f for f in jar.namelist() 
                         if any(f.endswith(ext) for ext in ['.map', '.evt', '.tdf', '.eif'])]
            
            for data_file in data_files:
                content = jar.read(data_file)
                # Keep the folder structure inside game_data to avoid clashes
                out_path = self.output_dir / 'game_data' / 'raw' / Path(data_file)
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(content)
        print(f"✓ Extraídos dados de jogo: {len(data_files)} arquivos")
    
    def generate_metadata(self):
        """Gera arquivo de metadados"""
        metadata = {
            "version": "0.0.2",
            "vendor": "Hands-On Mobile",
            "languages": ["pt-BR", "en-GB", "de-DE", "es-ES", "fr-FR"],
            "maps": 81,
            "enemies": 32,
            "npcs": 17,
            "items": 24,
            "music_tracks": 13,
        }
        
        out_path = self.output_dir / 'game_data' / 'metadata.json'
        out_path.parent.mkdir(parents=True, exist_ok=True)
        out_path.write_text(json.dumps(metadata, indent=2))
        print("✓ Metadados gerados")

if __name__ == '__main__':
    # Resolve paths relative to the project root
    project_root = Path(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
    jar_file = project_root / 'heroes-lore-pt-br' / 'Heroes Lore Wind Of Soltia [BR]240x320.jar'
    out_dir = project_root / 'assets'
    
    print(f"Extraindo de: {jar_file}")
    print(f"Para: {out_dir}")
    
    if not jar_file.exists():
        print("Arquivo JAR não encontrado!")
        exit(1)
        
    extractor = HeroesLoreExtractor(
        jar_path=str(jar_file),
        output_dir=str(out_dir)
    )
    extractor.extract_images()
    extractor.extract_audio()
    extractor.extract_game_data()
    extractor.generate_metadata()
