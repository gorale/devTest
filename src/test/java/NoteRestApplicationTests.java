import com.thevirtugroup.postitnote.dto.request.NoteDto;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import com.thevirtugroup.postitnote.service.NoteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class NoteRestApplicationTests {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void createTest() {
        Mockito.when(noteRepository.save(any())).thenAnswer((Answer<Note>) invocation -> {
            Object[] args = invocation.getArguments();
            Note saved = (Note) args[0];
            saved.setId(1);
            return saved;
        });
        NoteDto saved = noteService.save(new NoteDto("note name", "note text"));
        Mockito.verify(noteRepository).save(any());
        Assert.assertEquals(saved.getId(), 1);
    }

    @Test
    public void updateTest() {
        Mockito.when(noteRepository.update(any())).thenAnswer((Answer<Note>) invocation -> {
            Object[] args = invocation.getArguments();
            return (Note) args[0];
        });
        NoteDto updated = noteService.update(new NoteDto("note name", "note text"));
        Mockito.verify(noteRepository).update(any());
        Assert.assertEquals(updated.getId(), 0);
    }

    @Test
    public void deleteTest() {
        noteService.deleteById(1);
        Mockito.verify(noteRepository, Mockito.times(1)).deleteById(anyInt());
    }

    @Test
    public void getByIdTest() {
        Note note = new Note(1, "note name", "note text", LocalDateTime.now().toString());
        Mockito.when(noteRepository.findById(note.getId())).thenReturn(note);
        NoteDto availableNoteDto = noteService.getById(note.getId());
        Assert.assertEquals(availableNoteDto.getId(), note.getId());
    }

}


