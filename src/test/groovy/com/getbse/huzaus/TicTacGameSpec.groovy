package com.getbse.huzaus

import spock.lang.Specification
import spock.lang.Subject

class TicTacGameSpec extends Specification {

    @Subject
    TicTacGame theGame = new TicTacGame(3, 3)

    def "Should throw Exception when player provide value outside the bounds"() {
        when:
        theGame.validate(x, y)
        then:
        thrown(IllegalArgumentException)
        where:
        x  | y
        -1 | 2
        3  | 2
        2  | -1
        2  | 3
    }

    def "Should throw Exception when cell is occupied"() {
        when:
        theGame.play(0, 0)
        and:
        theGame.play(0, 0)
        then:
        thrown(IllegalArgumentException)
    }

    def "Should switch player after each turn"() {
        when:
        theGame.play(0, 0)
        then:
        theGame.lastPlayer == 'X' as char
    }

    def "Should declare vertical win for the second player"() {
        when: """X . .
                . . .
                . . ."""
        theGame.play(0, 0)
        and: """X O .
                . . .
                . . ."""
        theGame.play(1, 0)
        and: """X O .
                . . X
                . . ."""
        theGame.play(2, 1)
        and: """X O .
                . O X
                . . ."""
        theGame.play(1, 1)
        and: """X O .
                . O X
                . . X"""
        theGame.play(2, 2)
        then: """X O .
                 . O X
                 . O X"""
        theGame.play(1, 2) == 'win'
    }

    def "Should declare horizontal win for the first player"() {
        when: """X . .
                . . .
                . . ."""
        theGame.play(0, 0)
        and: """X . .
                O . .
                . . ."""
        theGame.play(0, 1)
        and: """X X .
                O . .
                . . ."""
        theGame.play(1, 0)
        and: """X X .
                O O .
                . . ."""
        theGame.play(1, 1)
        then: """X X X
                 O O .
                 . . ."""
        theGame.play(2, 0) == 'win'
    }
    def "Should declare right diagonal win for the first player"() {
        when: """X . .
                . . .
                . . ."""
        theGame.play(0, 0)
        and: """X . .
                O . .
                . . ."""
        theGame.play(0, 1)
        and: """X . .
                O X .
                . . ."""
        theGame.play(1, 1)
        and: """X . O
                O X .
                . . ."""
        theGame.play(2, 0)
        then: """X . O
                 O X .
                 . . X"""
        theGame.play(2, 2) == 'win'
    }

    def "Should declare draft"() {
        when: """X . .
                 . . .
                 . . ."""
        theGame.play(0, 0)
        and: """X . .
                . . O
                . . ."""
        theGame.play(2,1)
        and: """X . .
                . . O
                . . X"""
        theGame.play(2, 2)
        and: """X . .
                . O O
                . . X"""
        theGame.play(1, 1)
        and: """X . .
                X O O
                . . X"""
        theGame.play(0, 1)
        and: """X . .
                X O O
                O . X"""
        theGame.play(0, 2)
        and: """X . X
                X O O
                O . X"""
        theGame.play(2, 0)
        and: """X O X
                X O O
                O . X"""
        theGame.play(1, 0)
        then: """X O X
                 X O O
                 O X X"""
        theGame.play(1, 2) == 'draft'
    }
}
