import os
from PIL import Image, ImageDraw

def generate_icons():
    src_path = r'assets\logo_512.png'
    if not os.path.exists(src_path):
        src_path = r'C:\Users\david\Downloads\logo 512.png'
    if not os.path.exists(src_path):
        print("Source icon not found at", src_path)
        return

    base_img = Image.open(src_path).convert("RGBA")
    
    densities = {
        'mipmap-mdpi': 48,
        'mipmap-hdpi': 72,
        'mipmap-xhdpi': 96,
        'mipmap-xxhdpi': 144,
        'mipmap-xxxhdpi': 192,
    }

    res_dir = r'android\app\src\main\res'

    for folder, size in densities.items():
        out_dir = os.path.join(res_dir, folder)
        os.makedirs(out_dir, exist_ok=True)

        # Standard square/squircle icon
        resized = base_img.resize((size, size), Image.Resampling.LANCZOS)
        resized.save(os.path.join(out_dir, 'ic_launcher.png'), 'PNG')

        # Round icon (with circular mask)
        mask = Image.new('L', (size * 4, size * 4), 0)
        draw = ImageDraw.Draw(mask)
        draw.ellipse((0, 0, size * 4, size * 4), fill=255)
        mask = mask.resize((size, size), Image.Resampling.LANCZOS)

        round_img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
        round_img.paste(resized, (0, 0))
        round_img.putalpha(mask)
        round_img.save(os.path.join(out_dir, 'ic_launcher_round.png'), 'PNG')

        print(f"Generated {folder}: {size}x{size} (ic_launcher.png and ic_launcher_round.png)")

    # Also update AndroidManifest if roundIcon not set
    manifest_path = r'android\app\src\main\AndroidManifest.xml'
    with open(manifest_path, 'r', encoding='utf-8') as f:
        content = f.read()
    if 'android:roundIcon=' not in content:
        content = content.replace(
            'android:icon="@mipmap/ic_launcher"',
            'android:icon="@mipmap/ic_launcher"\n        android:roundIcon="@mipmap/ic_launcher_round"'
        )
        with open(manifest_path, 'w', encoding='utf-8') as f:
            f.write(content)
        print("Updated AndroidManifest.xml with android:roundIcon")

if __name__ == '__main__':
    generate_icons()
