import { Person } from "./person.model";
export class PersonResponse{
    _embedded: {
        personList: Person[];
    }
}