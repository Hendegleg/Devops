package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.IChambreService;
import java.util.List;


@RestController
//@CrossOrigin(origins = "http://192.168.186.130")

@CrossOrigin(origins = "${cors.allowed.origins}")


@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {

    IChambreService chambreService;

// http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
     @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }



    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    @PutMapping("/modify-chambre/{chambre-id}")
    public ResponseEntity<Chambre> modifyChambre(@PathVariable("chambre-id") Long chambreId, @RequestBody Chambre c) {
        try {
            c.setIdChambre(chambreId);  // Assurer que l'ID dans le corps de la requête est cohérent avec l'URL
            Chambre updatedChambre = chambreService.modifyChambre(c);
            return ResponseEntity.ok(updatedChambre);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Chambre non trouvée ou erreur
        }
    }



    @GetMapping("/trouver-chambres-selon-typ/{tc}")
    public List<Chambre> trouverChSelonTC(@PathVariable("tc") TypeChambre tc)
    {
        return chambreService.recupererChambresSelonTyp(tc);
    }













    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/trouver-chambre-selon-etudiant/{cin}")
    public Chambre trouverChSelonEt(@PathVariable("cin") long cin) {
        Chambre chambre = chambreService.trouverchambreSelonEtudiant(cin);
        return chambre;
    }


}
