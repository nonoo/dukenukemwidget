package com.nonoo.dukenukem.widget;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

public class DukeNukemWidgetService extends Service implements OnCompletionListener {

	int mediaPlayerNum = 0;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onStart( Intent intent, int startId ) {

		if( mediaPlayerNum > 3 )
			return;
		
		Random randGen = new Random();
		int soundNum = randGen.nextInt( 80 );
		int soundRes = 0;
		switch( soundNum ) {
		case 0: soundRes = R.raw.ahh04; break;
		case 1: soundRes = R.raw.ahmuch03; break;
		case 2: soundRes = R.raw.amess06; break;
		case 3: soundRes = R.raw.bitchn04; break;
		case 4: soundRes = R.raw.blowit01; break;
		case 5: soundRes = R.raw.booby04; break;
		case 6: soundRes = R.raw.born01; break;
		case 7: soundRes = R.raw.chew05; break;
		case 8: soundRes = R.raw.comeon02; break;
		case 9: soundRes = R.raw.con03; break;
		case 10: soundRes = R.raw.cool01; break;
		case 11: soundRes = R.raw.damn03; break;
		case 12: soundRes = R.raw.damnit04; break;
		case 13: soundRes = R.raw.dance01; break;
		case 14: soundRes = R.raw.diesob03; break;
		case 15: soundRes = R.raw.dmdeath; break;
		case 16: soundRes = R.raw.doomed16; break;
		case 17: soundRes = R.raw.dscrem04; break;
		case 18: soundRes = R.raw.dscrem15; break;
		case 19: soundRes = R.raw.dscrem16; break;
		case 20: soundRes = R.raw.dscrem17; break;
		case 21: soundRes = R.raw.dscrem18; break;
		case 22: soundRes = R.raw.dscrem38; break;
		case 23: soundRes = R.raw.duknuk14; break;
		case 24: soundRes = R.raw.eatsht01; break;
		case 25: soundRes = R.raw.exert; break;
		case 26: soundRes = R.raw.face01; break;
		case 27: soundRes = R.raw.force01; break;
		case 28: soundRes = R.raw.gasp; break;
		case 29: soundRes = R.raw.gasps07; break;
		case 30: soundRes = R.raw.getsom1a; break;
		case 31: soundRes = R.raw.gmeovr05; break;
		case 32: soundRes = R.raw.gothrt01; break;
		case 33: soundRes = R.raw.groovy02; break;
		case 34: soundRes = R.raw.gulp01; break;
		case 35: soundRes = R.raw.hail01; break;
		case 36: soundRes = R.raw.happen01; break;
		case 37: soundRes = R.raw.holycw01; break;
		case 38: soundRes = R.raw.holysh02; break;
		case 39: soundRes = R.raw.imgood12; break;
		case 40: soundRes = R.raw.inhell01; break;
		case 41: soundRes = R.raw.jones04; break;
		case 42: soundRes = R.raw.ktitx; break;
		case 43: soundRes = R.raw.letgod01; break;
		case 44: soundRes = R.raw.letsrk03; break;
		case 45: soundRes = R.raw.lookin01; break;
		case 46: soundRes = R.raw.myself3a; break;
		case 47: soundRes = R.raw.name01; break;
		case 48: soundRes = R.raw.needed03; break;
		case 49: soundRes = R.raw.nobody01; break;
		case 50: soundRes = R.raw.onlyon03; break;
		case 51: soundRes = R.raw.pain13; break;
		case 52: soundRes = R.raw.pain28; break;
		case 53: soundRes = R.raw.pain39; break;
		case 54: soundRes = R.raw.pain54; break;
		case 55: soundRes = R.raw.pain68; break;
		case 56: soundRes = R.raw.pain75; break;
		case 57: soundRes = R.raw.pain87; break;
		case 58: soundRes = R.raw.pain93; break;
		case 59: soundRes = R.raw.pay02; break;
		case 60: soundRes = R.raw.piece02; break;
		case 61: soundRes = R.raw.pisses01; break;
		case 62: soundRes = R.raw.pissin01; break;
		case 63: soundRes = R.raw.quake06; break;
		case 64: soundRes = R.raw.ready2a; break;
		case 65: soundRes = R.raw.ride06; break;
		case 66: soundRes = R.raw.rides09; break;
		case 67: soundRes = R.raw.rip01; break;
		case 68: soundRes = R.raw.ripem08; break;
		case 69: soundRes = R.raw.rnr01; break;
		case 70: soundRes = R.raw.rockin02; break;
		case 71: soundRes = R.raw.shake2a; break;
		case 72: soundRes = R.raw.sukit01; break;
		case 73: soundRes = R.raw.termin01; break;
		case 74: soundRes = R.raw.thsuk13a; break;
		case 75: soundRes = R.raw.waitin03; break;
		case 76: soundRes = R.raw.wansom4a; break;
		case 77: soundRes = R.raw.whipyu01; break;
		case 78: soundRes = R.raw.whistle; break;
		case 79: soundRes = R.raw.whrsit05; break;
		}

		MediaPlayer	mp = MediaPlayer.create( getApplicationContext(), soundRes );
		mp.setOnCompletionListener( this );
		mp.start();
		mediaPlayerNum++;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.release();
		mediaPlayerNum--;
		//Toast.makeText(getApplicationContext(), "release", Toast.LENGTH_SHORT).show();
	}
}
