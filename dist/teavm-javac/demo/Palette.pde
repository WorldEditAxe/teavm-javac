class Palette {
  float hue(float offset) {
    return (baseHue + offset + sin(pulse * 0.9 + offset * 0.04) * 22 + 360) % 360;
  }

  String modeName() {
    if (mode == MODE_GRAVITY) {
      return "gravity";
    }
    if (mode == MODE_VORTEX) {
      return "vortex";
    }
    return "flow";
  }
}
