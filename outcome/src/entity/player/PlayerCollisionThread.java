package entity.player;

import common.Commons;
import entity.tile.Tile;
import game.GameEventManager;
import game.GameSettings;
import main.Main;

public final class PlayerCollisionThread implements Runnable {

    private GameEventManager game;

    public PlayerCollisionThread(GameEventManager _game) {

        game = _game;

    }


    
    public final void detectTileCollisions(String dir) {

        // Detect collisions in determined direction (x or y)
        if (dir.equals("x")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, game.player.y - game.player.height * 2, game.player.y + game.player.height * 3) || 
                    Commons.inRange(tile.y + tile.height, game.player.y - game.player.height * 2, game.player.y + game.player.height * 3)) && 
                    (Commons.inRange(tile.x, game.player.x - game.player.width * 2, game.player.x + game.player.width * 3) || 
                    Commons.inRange(tile.x + tile.width, game.player.x - game.player.width * 2, game.player.x + game.player.width * 3))) {
                
                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the game.player is moving, set the position to align with the tile and zero
                        // the game.player velocity
                        if (game.player.rect.intersects(tile.rect)) {

                            if (game.player.velX < 0) {

                                game.player.x = tile.x + GameSettings.TILESIZE;
                                game.player.collidedLeft = true;

                            }

                            if (game.player.velX > 0) {

                                game.player.x = tile.x - GameSettings.TILESIZE;
                                game.player.collidedRight = true;

                            }

                            game.player.rect.x = game.player.x;
                            game.player.velX = 0;

                        } else {

                            // Set x collision properties to false if no collisions happen
                            game.player.collidedLeft = false;
                            game.player.collidedRight = false;

                        }

                    }

                }

            }

        }

        if (dir.equals("y")) {

            game.player.collisions.clear();

            // Loop through each tile and check if it is solid
            for (Tile tile : game.tilemap.getTiles()) {

                // Check if the tile is close to avoid checking collisions for every tile on the map
                if ((Commons.inRange(tile.y, game.player.y - game.player.height * 2, game.player.y + game.player.height * 3) || 
                    Commons.inRange(tile.y + tile.height, game.player.y - game.player.height * 2, game.player.y + game.player.height * 3)) && 
                    (Commons.inRange(tile.x, game.player.x - game.player.width * 2, game.player.x + game.player.width * 3) || 
                    Commons.inRange(tile.x + tile.width, game.player.x - game.player.width * 2, game.player.x + game.player.width * 3))) {

                    if (tile.type.equals("solid")) {

                        // If the tile is solid and the game.player collides with it, set the position to align with the tile and 
                        // zero the game.player velocity
                        if (game.player.rect.intersects(tile.rect)) {

                            if (game.player.velY < 0) {

                                game.player.y = tile.y + GameSettings.TILESIZE;
                                game.player.collidedUp = true;

                            }

                            if (game.player.velY > 0) {

                                game.player.y = tile.y - GameSettings.TILESIZE;
                                game.player.collidedDown = true;

                            }

                            game.player.rect.y = game.player.y;
                            game.player.velY = 0;

                        }
                        
                        if (game.player.groundRect.intersects(tile.rect)) {

                            game.player.collisions.add(true);

                        } else {
                            
                            game.player.collisions.add(false);
                        
                        }

                    }

                }

            }

            if (!game.player.collisions.contains(true)) {

                game.player.collidedDown = false;

            }

        }

    }



    public final void update() {

        game.player.rect.x = game.player.x;
        game.player.groundRect.x = game.player.x;
        detectTileCollisions("x");
        game.player.rect.y = game.player.y;
        game.player.groundRect.y = game.player.y + 8;
        detectTileCollisions("y");

    }



    @Override
    public void run() {

        while (true) {

            update();

        }

    }

}
