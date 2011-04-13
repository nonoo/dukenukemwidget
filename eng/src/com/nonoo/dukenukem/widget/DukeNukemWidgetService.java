package com.nonoo.dukenukem.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.IBinder;
import android.widget.RemoteViews;

public class DukeNukemWidgetService extends Service implements OnCompletionListener, OnErrorListener {

	private static final int MAXMEDIAPLAYERS = 3;

	private static HashMap<Integer, AnimatorThread> animatorThreads = new HashMap<Integer, AnimatorThread>();
	private static HashMap<Integer, ArrayList<MediaPlayer>> mediaPlayers = new HashMap<Integer, ArrayList<MediaPlayer>>();

	@Override
    public void onStart(Intent intent, int startId) {
		int appWidgetId = intent.getIntExtra("appWidgetId", -1);
		if( !animatorThreads.containsKey(appWidgetId) ) {
			AnimatorThread animatorThread = new AnimatorThread(this, appWidgetId);
			animatorThreads.put(appWidgetId, animatorThread);
			animatorThread.start();
		}
		if( canStartNewMediaPlayer() )
			playRandomSound(appWidgetId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private boolean canStartNewMediaPlayer() {
		int currentMediaPlayerCount = 0;
		for( Integer appWidgetId : mediaPlayers.keySet() )
			currentMediaPlayerCount += mediaPlayers.get(appWidgetId).size();

		if( currentMediaPlayerCount < MAXMEDIAPLAYERS )
			return true;
		return false;
	}
	
	private void playRandomSound(int appWidgetId) {
		Random randGen = new Random();
		int soundNum = randGen.nextInt( 60 );
		int soundRes = 0;
		switch( soundNum ) {
		default:
		case 0: soundRes = R.raw.ahmuch03; break;
		case 1: soundRes = R.raw.amess06; break;
		case 2: soundRes = R.raw.bitchn04; break;
		case 3: soundRes = R.raw.blowit01; break;
		case 4: soundRes = R.raw.booby04; break;
		case 5: soundRes = R.raw.born01; break;
		case 6: soundRes = R.raw.chew05; break;
		case 7: soundRes = R.raw.comeon02; break;
		case 8: soundRes = R.raw.con03; break;
		case 9: soundRes = R.raw.cool01; break;
		case 10: soundRes = R.raw.damn03; break;
		case 11: soundRes = R.raw.damnit04; break;
		case 12: soundRes = R.raw.dance01; break;
		case 13: soundRes = R.raw.diesob03; break;
		case 14: soundRes = R.raw.doomed16; break;
		case 15: soundRes = R.raw.duknuk14; break;
		case 16: soundRes = R.raw.eatsht01; break;
		case 17: soundRes = R.raw.face01; break;
		case 18: soundRes = R.raw.force01; break;
		case 19: soundRes = R.raw.getsom1a; break;
		case 20: soundRes = R.raw.gmeovr05; break;
		case 21: soundRes = R.raw.gothrt01; break;
		case 22: soundRes = R.raw.groovy02; break;
		case 23: soundRes = R.raw.hail01; break;
		case 24: soundRes = R.raw.happen01; break;
		case 25: soundRes = R.raw.holycw01; break;
		case 26: soundRes = R.raw.holysh02; break;
		case 27: soundRes = R.raw.imgood12; break;
		case 28: soundRes = R.raw.inhell01; break;
		case 29: soundRes = R.raw.jones04; break;
		case 30: soundRes = R.raw.ktitx; break;
		case 31: soundRes = R.raw.letgod01; break;
		case 32: soundRes = R.raw.letsrk03; break;
		case 33: soundRes = R.raw.lookin01; break;
		case 34: soundRes = R.raw.myself3a; break;
		case 35: soundRes = R.raw.name01; break;
		case 36: soundRes = R.raw.needed03; break;
		case 37: soundRes = R.raw.nobody01; break;
		case 38: soundRes = R.raw.onlyon03; break;
		case 39: soundRes = R.raw.pay02; break;
		case 40: soundRes = R.raw.piece02; break;
		case 41: soundRes = R.raw.pisses01; break;
		case 42: soundRes = R.raw.pissin01; break;
		case 43: soundRes = R.raw.quake06; break;
		case 44: soundRes = R.raw.ready2a; break;
		case 45: soundRes = R.raw.ride06; break;
		case 46: soundRes = R.raw.rides09; break;
		case 47: soundRes = R.raw.rip01; break;
		case 48: soundRes = R.raw.ripem08; break;
		case 49: soundRes = R.raw.rnr01; break;
		case 50: soundRes = R.raw.rockin02; break;
		case 51: soundRes = R.raw.shake2a; break;
		case 52: soundRes = R.raw.sukit01; break;
		case 53: soundRes = R.raw.termin01; break;
		case 54: soundRes = R.raw.thsuk13a; break;
		case 55: soundRes = R.raw.waitin03; break;
		case 56: soundRes = R.raw.wansom4a; break;
		case 57: soundRes = R.raw.whipyu01; break;
		case 58: soundRes = R.raw.whistle; break;
		case 59: soundRes = R.raw.whrsit05; break;
		}

		MediaPlayer mp = MediaPlayer.create( this, soundRes );
		if( mp == null )
			return;

		mp.setOnCompletionListener( this );
		mp.setOnErrorListener( this );

		if( !mediaPlayers.containsKey(appWidgetId) )
			mediaPlayers.put( appWidgetId, new ArrayList<MediaPlayer>(MAXMEDIAPLAYERS) );

		mediaPlayers.get(appWidgetId).add(mp);

		mp.start();
	}

	private boolean isSoundPlayingForWidget( int appWidgetId ) {
		if( !mediaPlayers.containsKey(appWidgetId) )
			return false;
		else {
			if( mediaPlayers.get(appWidgetId).size() == 0 )
				return false;
		}

		return true;
	}
	
	private int findAppWidgetIdForMediaPlayer( MediaPlayer mp ) {
		for( Integer appWidgetId : mediaPlayers.keySet() ) {
			if( mediaPlayers.get(appWidgetId).contains(mp) )
				return appWidgetId;
		}
		return -1;
	}
	
	@Override
	public void onCompletion( MediaPlayer mp ) {
		int appWidgetId = findAppWidgetIdForMediaPlayer( mp );
		mp.release();
		mediaPlayers.get(appWidgetId).remove(mp);

		for( Iterator<Integer> it = animatorThreads.keySet().iterator(); it.hasNext(); ) {
			Integer checkedAppWidgetId = it.next(); 

			if( !isSoundPlayingForWidget( checkedAppWidgetId ) ) {
				mediaPlayers.remove( checkedAppWidgetId );

				// stopping animation
				AnimatorThread animatorThread = animatorThreads.get(checkedAppWidgetId);
				animatorThread.interrupt();
				it.remove();

				RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.main);
				remoteViews.setImageViewResource(R.id.button, R.drawable.a001);
				AppWidgetManager.getInstance(this).updateAppWidget( checkedAppWidgetId, remoteViews );
			}
		}
	}

	@Override
	public boolean onError( MediaPlayer mp, int arg1, int arg2 ) {
		onCompletion( mp );
		return false;
	}
}
