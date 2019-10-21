import { Link } from "./link.model";
export class Person {
    id: string;
    createDate: string;
    dataAtualizacao: string;
    nome: string;
    sexo: string;
    email: string;
    dataNacimento: string;
    naturalidade: string;
    nacionalidade: string;
    cpf: string;
    _links: Link;

    constructor(){
        
    }
}