package com.codegym.service;

import com.codegym.dto.SongDto;
import com.codegym.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongService {
    Page<Song> findAll(Pageable pageable);

    void save(Song song);

    Song findById(int id);
}
