import { Checkpoint } from './checkpoint.model';
import { Participant } from './participant.model';

export interface Team {
  id?: number;
  teamName: string;
  eventId: number;
  participants: Participant[];
  checkpoints: Checkpoint[];
} 