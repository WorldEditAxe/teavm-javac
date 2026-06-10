class Palette3D {
  float hue(float offset) {
    return (baseHue + offset + sin(pulse * 0.8 + offset * 0.031) * 18 + 360) % 360;
  }
}
