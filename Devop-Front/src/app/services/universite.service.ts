import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Universite {
  idChambre: number;
  numeroChambre: number;
  typeC: string;
}

@Injectable({
  providedIn: 'root'
})
export class UniversiteService {
  private apiUrl = 'http://localhost:8089/tpfoyer/chambre';

  constructor(private http: HttpClient) {}

  getUniversites(): Observable<Universite[]> {
    return this.http.get<Universite[]>(`${this.apiUrl}/retrieve-all-chambres`);
  }

  addChambre(newChambre: Universite): Observable<Universite> {
    return this.http.post<Universite>(`${this.apiUrl}/add-chambre`, newChambre);
  }

  deleteChambre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/remove-chambre/${id}`);
  }

  updateChambre(chambre: Universite): Observable<Universite> {
    return this.http.put<Universite>(`${this.apiUrl}/modify-chambre/${chambre.idChambre}`, chambre);
  }
}