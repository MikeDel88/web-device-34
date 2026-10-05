import { Participation } from './Participation';

export type Olympic = {
  id: number;
  country: string;
  participations: Array<Participation>;
};
