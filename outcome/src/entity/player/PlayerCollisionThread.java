package entity.player;

import common.Commons;
import entity.tile.Tile;
import game.GameEventManager;
import game.GameSettings;

public class PlayerCollisionThread implements Runnable {

    private Player player;
    private GameEventManager game;

    public PlayerCollisionThread(Player player, GameEventManager game) {

        this.player = player;
        this.game = game;

    }


    
    public synchronized final void detectTileCollisions(String dir) {

        // Detect collisions in determined direction (x or y)
        if (dir.equals("x")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, player.y - player.height * 2, player.y + player.height * 3) || 
                    Commons.inRange(tile.y + tile.height, player.y - player.height * 2, player.y + player.height * 3)) && 
                    (Commons.inRange(tile.x, player.x - player.width * 2, player.x + player.width * 3) || 
                    Commons.inRange(tile.x + tile.width, player.x - player.width * 2, player.x + player.width * 3))) {
                
                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player is moving, set the position to align with the tile and zero
                        // the player velocity
                        if (player.rect.intersects(tile.rect)) {

                            if (player.velX < 0) {

                                player.x = tile.x + GameSettings.TILESIZE;
                                player.collidedLeft = true;

                            }

                            if (player.velX > 0) {

                                player.x = tile.x - GameSettings.TILESIZE;
                                player.collidedRight = true;

                            }

                            player.rect.x = player.x;
                            player.velX = 0;

                        } else {

                            // Set x collision properties to false if no collisions happen
                            player.collidedLeft = false;
                            player.collidedRight = false;

                        }

                    }

                }

            }

        }

        if (dir.equals("y")) {

            player.collisions.clear();

            // Loop through each tile and check if it is solid
            for (Tile tile : game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, player.y - player.height * 2, player.y + player.height * 3) || 
                    Commons.inRange(tile.y + tile.height, player.y - player.height * 2, player.y + player.height * 3)) && 
                    (Commons.inRange(tile.x, player.x - player.width * 2, player.x + player.width * 3) || 
                    Commons.inRange(tile.x + tile.width, player.x - player.width * 2, player.x + player.width * 3))) {

                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the player collides with it, set the position to align with the tile and 
                        // zero the player velocity
                        if (player.rect.intersects(tile.rect)) {

                            if (player.velY < 0) {

                                player.y = tile.y + GameSettings.TILESIZE;
                                player.collidedUp = true;

                            }

                            if (player.velY > 0) {

                                player.y = tile.y - GameSettings.TILESIZE;
                                player.collidedDown = true;

                            }

                            player.rect.y = player.y;
                            player.velY = 0;

                        }
                        
                        if (player.groundRect.intersects(tile.rect)) {

                            player.collisions.add(true);

                        } else {
                            
                            player.collisions.add(false);
                        
                        }

                    }

                }

            }

            if (!player.collisions.contains(true)) {

                player.collidedDown = false;

            }

        }

    }



    public void update() {

        player.rect.x = player.x;
        player.groundRect.x = player.x;
        detectTileCollisions("x");
        player.rect.y = player.y;
        player.groundRect.y = player.y + 8;
        detectTileCollisions("y");

    }



    @Override
    public void run() {
        
        update();

    }

}
