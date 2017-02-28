package br.com.codeshare.util;

import org.dozer.Mapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcqueide on 28/02/17.
 */
@Singleton
public class Conversor {

    @Inject
    private Mapper mapper;

    public <S, T> T converter(S entidade, Class<T> tipoVO) {
        if(entidade == null) {
            return null;
        } else {
            return mapper.map(entidade, tipoVO);
        }
    }

    public <S, T> List<T> converter(List<S> from, Class<T> voType) {
        ArrayList result = new ArrayList();
        if(from == null) {
            return result;
        } else {
            from.forEach(x -> result.add(mapper.map(x,voType)));
            return result;
        }
    }
}
