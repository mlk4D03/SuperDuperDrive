package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for getting data from the note table.
 */
@Service
public class NoteService {

    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note, Integer userid) {
        return this.noteMapper.insertNote(new Note(null, note.getNotetitle(), note.getDescription(), userid));
    }

    public boolean isNoteAlreadyAvailable(Integer noteId) {
        return this.noteMapper.getNote(noteId) != null;
    }

    public List<Note> getAllNotes() {
        return this.noteMapper.getAllNotes();
    }

    public int updateNote(String notetitle, String notedescription, Integer noteid) {
        return this.noteMapper.updateNote(notetitle, notedescription, noteid);
    }

    public int deleteNote(Integer noteid) {
        return this.noteMapper.deleteNote(noteid);
    }

    public Note getNote(Integer noteid) {
        return this.noteMapper.getNote(noteid);
    }
}
