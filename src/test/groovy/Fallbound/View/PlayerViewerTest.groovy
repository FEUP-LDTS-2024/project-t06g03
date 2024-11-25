package Fallbound.View

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.PlayerViewer
import spock.lang.Specification

class PlayerViewerTest extends Specification {

    def "player is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def player = new Player(new Vector(0, 0), Mock(Scene) as Scene)
        def playerViewer = new PlayerViewer()

        when:
        playerViewer.draw(gui, player)

        then:
        1 * gui.drawText(player.getPosition().toPosition(), "\u2588", Theme.FALLBOUND_BLUE)
    }
}