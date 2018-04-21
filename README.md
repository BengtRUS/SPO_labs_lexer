# SPO_labs_lexer
lang -> expr*
expr -> assign|while_c
assign -> VAR ASSIGN_OP value op_value
op_value -> (OP value)*
value -> DIGIT|VAR| br_expr
br_expr -> BR_O value (op_value)* BR_C
while_c -> WHILE_KW BR_O value COMP value BR_C DO_KW expr* END_KW
(циклы, скобочки, не вложенные)
