package com.message.response;

import com.message.response.model.Music;

public class MusicRespMessage extends BaseRespMessage {
	
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}
