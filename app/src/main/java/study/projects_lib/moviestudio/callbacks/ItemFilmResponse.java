package study.projects_lib.moviestudio.callbacks;

import study.projects_lib.moviestudio.model.ItemFilm;

public interface ItemFilmResponse {
    void itemContentFilm(ItemFilm parsingFilm);
    void itemUrlResponse(String urlFilm, int position);

    void itemContentResponse(ItemFilm itemFilm, int position);

}
