export function getParams(url: string, idx?: number) {
  const hasQuery = url.indexOf('?')
  let params
  if (hasQuery === -1)
    params = url.split('/').filter(i => Boolean(i))
  if ((params && idx) && idx < params.length)
    return params[idx]
  return params
}

export function toS(target: unknown): string {
  return JSON.stringify(target)
}

export function toO(target: string) {
  return JSON.parse(target)
}
