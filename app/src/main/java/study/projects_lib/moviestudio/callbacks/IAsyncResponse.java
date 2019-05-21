package study.projects_lib.moviestudio.callbacks;

import java.util.List;

import study.projects_lib.moviestudio.model.ItemFilm;

public interface IAsyncResponse {

    void processFinish(List<ItemFilm> output);

}
