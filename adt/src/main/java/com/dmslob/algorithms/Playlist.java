package com.dmslob.algorithms;

public class Playlist {
    public static class Song {
        private String name;
        private Song nextSong;

        public Song(String name) {
            this.name = name;
        }

        public void setNextSong(Song nextSong) {
            this.nextSong = nextSong;
        }

        public boolean isRepeatingPlaylist() {
            boolean ex = false;

            while (nextSong != null) {
                if (name.equals(nextSong.name)) {
                    ex = true;
                    break;
                }
                nextSong = nextSong.nextSong;
            }
            return ex;
        }
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}
