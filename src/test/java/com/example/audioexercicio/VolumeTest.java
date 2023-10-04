package com.example.audioexercicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VolumeTest {

// 1 - Crie um mock da classe AudioManager

// 2 - injete um mock da classe VolumeUtil

    @Mock
    AudioManager audioManager;

    @InjectMocks
    VolumeUtil volumeUtil;

    @Test
    public void testAudioManagerSetVolume() {

        doNothing().when(audioManager).setVolume(5);

        volumeUtil.maximizeVolume(5);

        verify(audioManager, times(1)).setVolume(5);

    }
}
