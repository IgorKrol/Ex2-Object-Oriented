

Authors: Igor Krol & Amit Kazado
Project's Name: Pacman
This program was created with the eclipse IDE, in December 2018.

Main Idea:
This is a version of the nostalgic Pacman game, only that here there can be more than 1 Pacman, and there are no ghosts, only Fruits to be eaten by the Pacmans.
the game is "located" in a map of Ariel University, Israel.
Each Pacman has speed and coordinates, each Fruit has weight and coordinates.
The goal of this application is to find the shortests paths available given some Pacmans and Fruits, and move them accordingly.

The user of this application can choose how many Pacmans he wants to add to the game, and how many Fruits he wishes to add (there's "Add Pacman" and "Add Fruit" options in the game's main menu).
If the user regrets his choice, he can erase all of the drawn Pacmans and Fruits with "Reset" button on the menu.

Once the user has created his game of Pacmans and Fruits, he can either choose "Run" -> "Find Short Path" will draw lines from Pacmans to Fruits to show the fastes lanes for this specific game.
In order to get the Pacmans moving, you'll need to choose "Run" -> "Start Game".

Each game can be saved either as a csv file, which can be loaded as well later, and there is an option of saving the game as a kml file (in order to load them to google earth).


Project's Structure:
There are 9 libararies in this project: Geom, GIS, Coords, Game Components, Gui, File Format, Threads, Resources, Algo.

* Geom - contains: Point2D and Point3D classes, which are used for saving location data.
* GIS - contains: Element, Layer, Meta Data and Project classes, which are used to manage and create csv and kml files.
* Coords - contains: MyCoords class, which is used to calculate distance/vectors/elevation/addition between two Points.
* Game Components - contains: Figure, Pacman, Fruit, Game, Path classes.
* Gui - contains: MyFrame class, which uses all of the project's libararies and give them "game functionallity" and design, Pacmans are yellow circles, Fruits are smaller red circles, Paths are blue lines between the circles.
* File Format - contains: csv2kml, CSVReader, gameToCSVWriter, KMLWriter, Path2KML classes, which allows the user to save and load games in this program through different convertion techniques.
* Algo - contains: ShortestPathAlgo class, which gets a Game object(contains Pacmans and Fruits) and calcs the shortest path available for each pacman to take, while considering each Pacman's speed, 
and both the coordinates of all Pacmans and Fruits.
* Threads - contains: myThread class, which gives movement to the Pacmans on the map, works according to ShortestPathAlgo's calcs.
* Resources - contains: map class, which is responsible for the convertion of "real-life" coordinates from the given map (picture of  Ariel University) to game Pixels and the other way around.
