# T3RectangleIntersection
В основном, проблема заключается в том, что область пересечения постоянно изменяется от малейших изменений в положении прямоугольников (а иногда даже без него, я специального для этого прикрутил репейнт к любому движению мыши), а также в том, что
проверки, зачастую, блокируют друг друга. Так, например, есть в программе twoCornerInRect1, twoCornerInRect2 и twoCornerInRectWBugs. Первые два работают идеально - но блокируют работу друг друга, последний работает кое-как, зато совмещает первые два. Я чего только не пробовал, но постоянно упираюсь в новые проблемы. Может быть, это просто исправить,
но я не вижу как, потому что уже устал
Если что, isChanged не делает ничего, это осталось от попытки фиксить все это
isCrossed и isCrossed2 различаются строгим и нестрогим сравнением
А сам класс Rectangle, по заверениям моего браузера, может навредить компьютеру. Знак качества
