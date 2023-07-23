export interface IMetaData{
    section: string,
    order?: number,
    components: any[]
}

export interface ICompomentMeta{
    key: string,
    type: any,
    label: string,
    rule: any,
    order?:number
}