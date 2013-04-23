package privmem.md5scj;

/**
 *  This file is part of oSCJ.
 *
 *   oSCJ is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   oSCJ is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with oSCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *   Copyright 2009, 2010
 *   @authors  Lei Zhao, Ales Plsek
 */

import javax.safetycritical.CyclicExecutive;
import javax.safetycritical.CyclicSchedule;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

import privmem.md5scj.com.twmacinta.util.MyMD5Input;


public class MD5SCJ extends PeriodicEventHandler {

    public MD5SCJ(long psize, int count) {
        super(null, null, new StorageParameters(psize,null), 1000);
        count_ = count;
    }

    private int count_;

    /**
     *
     * Testing Enter Private Memory
     *
     */
    @Override
    public void handleAsyncEvent() {
        long start = System.nanoTime();
        doMD5work();
        long end = System.nanoTime();

        if (count_-- == 0)
            Mission.getCurrentMission().requestSequenceTermination();
    }

    private void doMD5work() {
        for (String in : Constants.input) {
            MyMD5Input myMD = new MyMD5Input();
            myMD.run(in);
            //myMD.finalHash(in);
        }
    }

    @Override
    public void cleanUp() {
    }
}
