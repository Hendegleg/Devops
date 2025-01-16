package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    ChambreRepository chambreRepository;

    public List<Chambre> retrieveAllChambres() {
        log.info("In Methodo retrieveAllChambres : ");
        List<Chambre> listC = chambreRepository.findAll();
        log.info("Out of retrieveAllChambres : ");

        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        Chambre c = chambreRepository.findById(chambreId).get();
        return c;
    }

    public Chambre addChambre(Chambre c) {
        Chambre chambre = chambreRepository.save(c);
        return chambre;
    }


    public Chambre modifyChambre(Chambre c) {
        log.info("Attempting to modify chambre with ID: {}", c.getIdChambre());

        // Vérifie si la chambre existe avant de la modifier
        Chambre existingChambre = chambreRepository.findById(c.getIdChambre()).orElseThrow(() ->
                new RuntimeException("Chambre with ID " + c.getIdChambre() + " not found"));

        // Met à jour les champs nécessaires ici si besoin
        existingChambre.setNumeroChambre(c.getNumeroChambre());
        existingChambre.setTypeC(c.getTypeC());
        existingChambre.setReservations(c.getReservations());
        existingChambre.setBloc(c.getBloc());

        log.info("Modified chambre with ID: {}", existingChambre.getIdChambre());
        return chambreRepository.save(existingChambre);
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }







    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc)
    {
        return chambreRepository.findAllByTypeC(tc);
    }






















    public Chambre trouverchambreSelonEtudiant(long cin) {
       //

        return chambreRepository.trouverChselonEt(cin);
    }
}
