import { fileURLToPath } from 'node:url'

function resolveEntryForPkg(prefix: string) {
  return fileURLToPath(new URL(`../${prefix}`, import.meta.url))
}

const entries = {
  '@': resolveEntryForPkg('src'),
  '@elven/ui': resolveEntryForPkg('../components/src/components'),
  '@stores': resolveEntryForPkg('src/stores'),
  '@components': resolveEntryForPkg('src/components'),
  '@api': resolveEntryForPkg('src/api'),
}

export { entries }
