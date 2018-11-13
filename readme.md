<h1>Game of Life<h1>
<h2>Abstract<h2>
<p>Das Spiel „Game of Life“ basiert auf der gleichnamigen Idee von John Horton Conway. Das Spielfeld besteht aus einem Gitter einzelner quadratischer Zellen. Jede Zelle kann tot oder lebendig sein. Vier simple Regeln entscheiden über diesen Zustand einer Zelle. Dies hängt von dem Zustand seinen di-rekten acht Nachbarn ab.<p>
<h2>Regeln<h2>
<p>Falls eine Zelle tot ist, kann sie nur wieder lebendig werden, wenn sie genau drei lebendige Nachbarn hat.
Ist die Zelle lebendig, wird sie sterben, wenn weniger als 2 oder mehr als 3 Nachbarn hat. Beudeutet, dass eine lebendige Zelle mit 2 oder 3 Nachbarn ihren Zustand nicht verändern wird.<p>
<h2>Models<h2>
<h3>Cell<h3>
<p>Das wichtigste Model ist die Zelle. Sie erbt von einem JPanel. Sie besitzt zwei Attribute:<p>
  *	boolean isAlive : Hier wird sich den Zustand der Zelle gemerkt.
  *	int aliveNeighbours: Hier wird vermerkt, wie viele lebendige Nachbarn die Zelle hat.
<h2>View<h2>
<h3>MainFrame<h3>
Erbt von JFrame und besteht hauptsächlich aus: 
  *	JPanel controlPanel : Beinhaltet die zwei JButtons und JLabels, von hier wird der Spielfluss gesteuert.
  *	JButton pauseStartButton : Damit kann beim Klicken die Simulation unterbrochen/fortgesetzt werden.
  *	JButton resetButton : Tötet alle Zellen und setzt den „GenerationCounter auf 0. 
  *	JLabel generationCounterLabel : Inkrementiert sich bei jeder neuen Generation.
  *	GameGrid gameGrid : Erbt von JPanel und besitzt ein GridLayout. Hier werden die Zellen an-geordnet.
<h2>Controller<h2>
CellController
Dieser Controller initiiert in seinem Konstruktor einen 2-dimensionalen Cell-Array und füllt diesen auf. Diese Klasse enthält die meiste Logik des Spiels.
Methoden
countAliveNeighbours()
Diese Methode geht durch den gesamten cells[][] und geht mittels um je ±1 abweichende Rows und Cols durch jeden Nachbarn und zählt mit, wie viele davon leben. Die errechnete Zahl wird direkt im Attribut aliveNeighbours der Zelle gepeichert.
Dazu kam die Schwierigkeit, dass eine Zelle am Anfang/Ende von einer Row/Col nicht 8 sondern 5 Nachbarn hat, bzw. wenn es im Ecken des Grid ist, sogar nur 3 Nachbarn. Dies resultierte in kompli-zierten IF-Verzweigungen.
judgeCells()
Hier wurde die Tatsächlichen Spielregeln implementiert. Es wird wieder durch jede Zelle gegangen, diesmal wird aber das Attribut isAlive abgerufen um zu wissen, ob die Zelle entweder sterben oder auferstehen könnte. 
Danach werden durch IF-Verzweigungen nach den Regeln die Zustandsänderung der nächsten Ge-neration der Zelle festgestellt.
GameGridController
Dieser Controller besitzt hauptsächlich die zwei Constants:
*	static final int GRIDCOLS
*	static final int GRIDROWS
Sie stellen die grösse des Rasters vor und so auch die Anzahl Cells, die initiiert werden. Durch die einfache Änderung dieser zwei Attributen, lässt sich das Spiel beliebig gross verändern.
<h2>Listener<h2>
ButtonListener
Dieser Listener wird den beiden JButtons pauseStartButton und resetButton zugeteilt.
Der pauseStartButton erfüllt vier Funktionen:
*	Wenn mitten im Spiel, dann wird es beim Klick jediglich pausiert.
*	Wenn das Spiel pausiert ist, wird das Spiel fortgesetzt.
*	Wenn das Spielfeld zurückgesetzt wurde, kann es mit dem Button neugestartet werden.
*	Wenn es ein „Game Over“ gab, wird das Spiel mit dem pauseStartButton neu gestartet.
CellListener
Dieser erbt vom MouseAdapter. Er verarbeitet ein mousePressed() auf einer Zelle und tötet oder er-weckt sie dem entsprechend.
<h2>Thread<h2>
GameThread
Dieser Thread arbeitet das Spielgeschehen ab. 
Constants
Mit dem Constant GENERATION_TIME_MILLIS wird die Simulationsgeschwindigkeit in Millisekunden bestimmt. In der aktuellen Implementierung ist sie auf 50 Millisekunden gesetzt.
Attribute
*	boolean paused : wird in einer while-Schlaufe in run() geprüft.
*	boolean gameOver : wird ebenfalls in einer höheren while-Schlaufe geprüft.
*	int generationCounter : wird nach jeder Generationsberechnung inkrementiert.
Methoden
run()
Hier führt der Thread die Methoden cellController.countAliveNeighbours() und cellControl-ler.judgeCells() ausgeführt, die zwei Methoden, welche das eigentliche Spielgeschehen berechnen.
checkGameOver()
Wird ebenfalls in run() ausgeführt und geht durch alle Zellen durch, bis er eine Lebende gefunden hat. Falls alle Zellen tot sind, ändert er gameOver und paused auf true und der Thread unterbricht das Spielgeschehen.
