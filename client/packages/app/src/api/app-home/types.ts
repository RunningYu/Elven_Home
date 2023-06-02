export interface IreqHotList {
  page: number
  size: number
  token: string | number | null
}

export interface IreqElvenList {
  token: string
  kind: string
  page: number
  size: number
}
