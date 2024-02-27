/*==========================================================

 Title:       Assignment 5 - Sum/Difference with Pointer
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <09/27/23>
 Description: Sum/Difference with Pointer

 ==========================================================
*/
#include <iostream>
#include <cmath>

using namespace std;



void Modify(int *x, int *y) {
    // Complete this Function
    
    int ptr_x = *x;
    int ptr_y = *y;
    
    *x = ptr_x + ptr_y;
    *y = abs(ptr_x - ptr_y);
    
    
}

int main() {
    //DO NOT MODIFY ANYTHING IN THE main() !!!
    
    int x, y;
    
    int *ptr_x = &x;
    int *ptr_y = &y;
    
    cin >> x;
    cin >> y;
    
    Modify(ptr_x, ptr_y);
    
    cout << x << endl;
    cout << y;
                 
    return 0;
}
