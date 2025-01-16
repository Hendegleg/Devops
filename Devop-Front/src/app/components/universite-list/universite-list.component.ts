import { Component, OnInit } from '@angular/core';
import { UniversiteService } from '../../services/universite.service';

interface Universite {
  idChambre: number;
  numeroChambre: number;
  typeC: string;
}

@Component({
  selector: 'app-universite-list',
  templateUrl: './universite-list.component.html',
  styleUrls: ['./universite-list.component.css']
})
export class UniversiteListComponent implements OnInit {
  chambres: Universite[] = [];
  newChambre: Universite = { idChambre: 0, numeroChambre: 0, typeC: '' };
  editingChambre: Universite | null = null; // Propriété pour la chambre en édition

  constructor(private universiteService: UniversiteService) {}

  ngOnInit(): void {
    this.universiteService.getUniversites().subscribe(
      (data) => {
        this.chambres = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des chambres:', error);
      }
    );
  }

  onSubmit(): void {
    this.universiteService.addChambre(this.newChambre).subscribe(
      (addedChambre) => {
        this.chambres.push(addedChambre);
        this.newChambre = { idChambre: 0, numeroChambre: 0, typeC: '' };
      },
      (error) => {
        console.error('Erreur lors de l’ajout de la chambre:', error);
      }
    );
  }

  deleteChambre(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette chambre ?')) {
      this.universiteService.deleteChambre(id).subscribe(
        () => {
          this.chambres = this.chambres.filter(chambre => chambre.idChambre !== id);
          console.log('Chambre supprimée avec succès');
        },
        (error) => {
          console.error('Erreur lors de la suppression de la chambre:', error);
        }
      );
    }
  }

  // Nouvelle méthode pour initier l'édition
  editChambre(chambre: Universite): void {
    this.editingChambre = { ...chambre }; // Copie de la chambre pour l'édition
  }

  // Méthode pour annuler l'édition
  cancelEdit(): void {
    this.editingChambre = null;
  }

  // Méthode pour mettre à jour la chambre
  onUpdateSubmit(): void {
    if (this.editingChambre) {
      this.universiteService.updateChambre(this.editingChambre).subscribe(
        (updatedChambre) => {
          const index = this.chambres.findIndex(c => c.idChambre === updatedChambre.idChambre);
          if (index !== -1) {
            this.chambres[index] = updatedChambre; // Remplacer la chambre dans la liste
          }
          this.editingChambre = null; // Réinitialiser l'édition
        },
        (error) => {
          console.error('Erreur lors de la mise à jour de la chambre:', error);
        }
      );
    }
  }
}