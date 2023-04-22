//package ajc.formation.spring.bibliotheque.restcontrollers;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.fasterxml.jackson.annotation.JsonView;
//
//import ajc.formation.spring.bibliotheque.entities.Administrateur;
//import ajc.formation.spring.bibliotheque.jsonviews.JsonViews;
//import ajc.formation.spring.bibliotheque.services.AdministrateurService;
//
//@RestController
//@RequestMapping("/api/administrateur")
//public class AdministrateurRestController {
//	@Autowired
//	private AdministrateurService administrateurSrv;
//
//	@GetMapping("")
//	@JsonView(JsonViews.Simple.class)
//	public List<Administrateur> getAll() {
//		return administrateurSrv.getAll();
//	}
//
//	@GetMapping("/{id}")
//	@JsonView(JsonViews.Simple.class)
//	public Administrateur getById(@PathVariable Long id) {
//		Administrateur administrateur = null;
//		administrateur = administrateurSrv.getById(id);
//		return administrateur;
//	}
//
//
//	@PostMapping("")
//	@JsonView(JsonViews.Simple.class)
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public Administrateur create(@Valid @RequestBody Administrateur administrateur, BindingResult br) {
//		if (br.hasErrors()) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//		}
//		administrateurSrv.createOrUpdate(administrateur);
//		return administrateur;
//	}
//
//	@PutMapping("/{id}")
//	@JsonView(JsonViews.Simple.class)
//	public Administrateur update(@RequestBody Administrateur administrateur, @PathVariable Long id) {
//		Administrateur administrateurEnBase = administrateurSrv.getById(id);
//		administrateurSrv.createOrUpdate(administrateurEnBase);
//		return administrateurEnBase;
//	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) {
//		administrateurSrv.deleteById(id);
//	}
//}