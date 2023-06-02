import { defineConfig, presetAttributify, presetIcons, presetUno, presetWebFonts } from 'unocss'

export default defineConfig({
  theme: {
    colors: {
      primary: '#03ae67',
    },
  },
  presets: [
    presetUno(),
    presetAttributify(),
    presetWebFonts(),
    presetIcons({
      scale: 1.2,
      extraProperties: {
        'min-width': '1.2em',
      },
    }),
  ],
})
