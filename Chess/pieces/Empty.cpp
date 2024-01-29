//
// Created by Hayden Collins on 2/21/23.
//

#include "Empty.h"

Empty::Empty(bool color) : Piece(color) {
    piece_type = 'E';
    unicode = ".";
    side = color;
    has_moved = true;
}

vector<int> Empty::getAttackedSquares(int initial_pos, vector<unique_ptr<Piece>> &board) {
    return vector<int>{};
}
