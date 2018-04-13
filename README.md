# SPO_labs_lexer
lang -> expr*
expr -> VAR ASSIGN_OP value op_value
op_value -> (OP value)*
value -> DIGIT|VAR
(циклы, скобочки, не вложенные)
