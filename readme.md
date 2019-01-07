# Game of Life
## Abstract
Das Spiel „Game of Life“ basiert auf der gleichnamigen Idee von John Horton Conway. Das Spielfeld besteht aus einem Gitter einzelner quadratischer Zellen. Jede Zelle kann tot oder lebendig sein. Vier simple Regeln entscheiden über diesen Zustand einer Zelle. Dies hängt von dem Zustand seiner direkten acht Nachbarn ab.
## Regeln
Die Regeln lassen sich über die Einstellungen während Runtime ändern.<br>
Die Standardregeln sind:<br>
Falls eine Zelle tot ist, kann sie nur wieder lebendig werden, wenn sie genau drei lebendige Nachbarn hat. Ist die Zelle lebendig, wird sie sterben, wenn weniger als 2 oder mehr als 3 Nachbarn hat. Beudeutet, dass eine lebendige Zelle mit 2 oder 3 Nachbarn ihren Zustand nicht verändern wird.
## Models
### Cell
Das wichtigste Model ist die Zelle. Sie erbt von einem JPanel. Sie besitzt zwei Attribute:
  *	boolean isAlive : Hier wird sich den Zustand der Zelle gemerkt.
  *	int aliveNeighbours: Hier wird vermerkt, wie viele lebendige Nachbarn die Zelle hat.
## View
### IMainFrame
Ein Interface welche alle MainFrames verschiedener Technologien implementieren müssen.<br>
Methoden:
  * buildGUI() : void
  * updateGUI() : void
### AbstractSwingMainFrame
Implementiert das IMainFrame. Alle MainFrames der Technologie "Swing" müssen von dieser abstrakten Klasse erben.<br>
Attribute: <br>
  * protected Locale currentLocale
  * protected ResourceBundle rBundle
Methoden: <br>
  * public drawGridLines() : void
  * public drawGrid() : void
### SwingMainFrame
Erbt von AbstractSwingMainFrame und besteht hauptsächlich aus: 
  *	JPanel controlPanel : Beinhaltet die zwei JButtons und JLabels, von hier wird der Spielfluss gesteuert.
  *	JButton pauseStartButton : Damit kann beim Klicken die Simulation unterbrochen/fortgesetzt werden.
  *	JButton resetButton : Tötet alle Zellen und setzt den "GenerationCounter" auf 0. 
  * JButton settingsButton : Öffnet ein JDialog-Fenster.
  * JButton loadButton / saveButton : speichert / ladet den Spielstand.
  *	JLabel generationCounterLabel : Inkrementiert sich bei jeder neuen Generation.
  *	GameGrid gameGrid : Erbt von JPanel und besitzt ein GridLayout. Hier werden die Zellen an-geordnet.
### SwingSettingsDialog
Öffnet sich, wenn der settingsButton im MainFrame gedruckt wurde. Beinhaltet einen JButton über den sich ein weiteres JDialog öffnet, über das man die Regeln definieren kann.
### SwingNewRuleDialog
Über vier Textfelder, welche auf Nummern validiert sind, können die Regeln definiert werden.
## Controller
### CellController
Dieser Controller initiiert in seinem Konstruktor einen 2-dimensionalen Cell-Array und füllt diesen auf. Diese Klasse enthält die meiste Logik des Spiels.
#### Methoden
##### countAliveNeighbours()
Diese Methode geht durch den gesamten cells[][] und geht mittels um je ±1 abweichende Rows und Cols durch jeden Nachbarn und zählt mit, wie viele davon leben. Die errechnete Zahl wird direkt im Attribut aliveNeighbours der Zelle gepeichert. Dazu kam die Schwierigkeit, dass eine Zelle am Anfang/Ende von einer Row/Col nicht 8 sondern 5 Nachbarn hat, bzw. wenn es im Ecken des Grid ist, sogar nur 3 Nachbarn. Dies resultierte in kompli-zierten IF-Verzweigungen.
##### judgeCells()
Hier wurde die Tatsächlichen Spielregeln implementiert. Es wird wieder durch jede Zelle gegangen, diesmal wird aber das Attribut isAlive abgerufen um zu wissen, ob die Zelle entweder sterben oder auferstehen könnte. Danach werden durch IF-Verzweigungen nach den Regeln die Zustandsänderung der nächsten Ge-neration der Zelle festgestellt.
### GameGridController
Dieser Controller besitzt hauptsächlich die zwei Constants:
 *	static final int GRIDCOLS
 *	static final int GRIDROWS
Sie stellen die grösse des Rasters vor und so auch die Anzahl Cells, die initiiert werden. Durch die einfache Änderung dieser zwei Attributen, lässt sich das Spiel beliebig gross verändern.
## Listener
### GameActionListener
Dieser Listener wird den beiden JButtons pauseStartButton und resetButton zugeteilt.<br>
Der pauseStartButton erfüllt vier Funktionen:
*	Wenn mitten im Spiel, dann wird es beim Klick jediglich pausiert.
*	Wenn das Spiel pausiert ist, wird das Spiel fortgesetzt.
*	Wenn das Spielfeld zurückgesetzt wurde, kann es mit dem Button neugestartet werden.
*	Wenn es ein „Game Over“ gab, wird das Spiel mit dem pauseStartButton neu gestartet.
### GridListener
Dieser erbt vom MouseAdapter. Er verarbeitet ein mousePressed() und ein mouseDragged() auf einer Zelle und tötet die Zelle bei einem Rechtsklick/-drag und erweckt sie bei einem Linksklick/-drag.
### SaveListener
Im Konstruktor wird vom SaveManagerFactory eine verfügbare SaveManager-Implementierung bereitgestellt. Bei einem load/save wird dann über das Interface ISafeManager die Aktion durchgeführt.
### RuleButtonListener
Wird im SwingNewRuleDialog and den zwei Buttons für das sichern der Regeleingaben oder Standardregeln hinzugefügt.
## Thread
### GameThread
Dieser Thread arbeitet das Spielgeschehen ab. 
#### Constants
Mit dem Constant GENERATION_TIME_MILLIS wird die Simulationsgeschwindigkeit in Millisekunden bestimmt. In der aktuellen Implementierung ist sie auf 50 Millisekunden gesetzt.
#### Attribute
*	boolean paused : wird in einer while-Schlaufe in run() geprüft.
*	boolean gameOver : wird ebenfalls in einer höheren while-Schlaufe geprüft.
*	int generationCounter : wird nach jeder Generationsberechnung inkrementiert.
#### Methoden
##### run()
Hier führt der Thread die Methoden cellController.countAliveNeighbours() und cellControl-ler.judgeCells() ausgeführt, die zwei Methoden, welche das eigentliche Spielgeschehen berechnen.
##### checkGameOver()
Wird ebenfalls in run() ausgeführt und geht durch alle Zellen durch, bis er eine Lebende gefunden hat. Falls alle Zellen tot sind, ändert er gameOver und paused auf true und der Thread unterbricht das Spielgeschehen.
## Persistence
Es ist möglich den Spielstand auf einer verbundenen Datenbank oder auf dem Speichersystem zu speichern und zu laden.
### ISaveManager
Das Interface von welchen alle Speichermöglichkeiten ihre 3 Standard-Methoden:
 * saveGame() : void
 * loadGame() : void
 * testAvailability : void
### AbstractSaveManager
Diese abstrakte Klasse implementiert ISaveManager. Dazu kommt noch ein Cell[][]-Attribut und dazu gehörige Getter und Setter.
### SaveManager Implementierungen
#### LocalFileSystemManager
Erbt von AbstractSaveManager und implementiert die Methoden von ISaveManager. Um den Spielstand in einem File abspeichern zu können, schreibt das Programm in ein Textfile. Für jede lebende Zelle eine "1" und für eine tote eine "0". Welches beim Laden wiederum gelesen wird.
#### DatabaseManager
Erbt von AbstractSaveManager und implementiert die Methoden von ISaveManager. Der Spielstand wird in einer Tabelle auf einer Datenbank gespeichert. Dabei stellt eine Datenbankzeile eine Zelle im Spiel da. In der ersten Datenbankspalte ist die CellId gespeichert und in der zweiten ein Boolean, ob die Zelle lebt oder nicht.
##### Hibernate
Dabei wird die Hibernate-Technologie verwendet. Das Model Cell wird gemappt und direkt in die Datenbank gespeichert.
### SaveManagerFactory
Testet die Anwendung der verschiedenen Speichermöglichkeiten und gibt eine funktionierende SaveManager-Implementierung zurück.
## Internationalisierung
Mit Hilfe dem ResourceBundle und des Locales werden die korrekten Übersetzungen aus den jeweiligen MessagesBundle-Properties geholt.
 * MessagesBundle_de.properties
 * MessagesBundle_en.properties
 * MessagesBundle.properties
