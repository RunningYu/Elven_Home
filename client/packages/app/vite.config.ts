import { resolve } from 'node:path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import UnoCSS from 'unocss/vite'
import { entries } from './scripts/aliases'

// vite.config.ts

export default defineConfig(({ mode }) => {
  return {
    base: './',
    plugins: [vue({
      script: {
        defineModel: true,
      },
    }),
    UnoCSS(), // ...
    AutoImport({
      imports: ['vue'],
    })],
    define: mode === 'mock'
      ? {
          __MOCK__: true,
        }
      : {},
    envDir: resolve(__dirname, './env'),
    envPrefix: 'Elven',
    resolve: {
      alias: entries,
    },
  }
})
