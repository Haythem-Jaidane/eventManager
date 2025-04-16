import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Checkpoint } from '../models/checkpoint.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckpointService {
  private apiUrl = `${environment.apiUrl}/Checkpoints`;

  constructor(private http: HttpClient) { }

  getEventCheckpoints(eventId: number): Observable<Checkpoint[]> {
    return this.http.get<Checkpoint[]>(`${this.apiUrl}`);
  }

  getTeamCheckpoints(teamId: number): Observable<Checkpoint[]> {
    return this.http.get<Checkpoint[]>(`${this.apiUrl}/team/${teamId}`);
  }

  addCheckpoint(checkpoint: Checkpoint): Observable<Checkpoint> {
    return this.http.post<Checkpoint>(`${this.apiUrl}/add`, checkpoint);
  }

  updateCheckpointStatus(checkpointId: number, status: string): Observable<Checkpoint> {
    return this.http.patch<Checkpoint>(`${this.apiUrl}/${checkpointId}/status`, { status });
  }

  deleteCheckpoint(checkpointId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${checkpointId}`);
  }
}
