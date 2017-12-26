package br.com.armazem21.store.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.stereotype.Component;

import br.com.armazem21.store.model.Cerveja;
import br.com.armazem21.store.storage.FotoStorage;

@Component
public class CervejaEntityListener {

//	@Autowired
//	private FotoStorage fotoStorage;

	@PostLoad
	public void postLoad(final Cerveja cerveja) {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		cerveja.setUrlFoto(FotoStorage.URL + cerveja.getFotoOuMock());
		cerveja.setUrlThumbnailFoto(FotoStorage.URL + FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock());
	}

}
