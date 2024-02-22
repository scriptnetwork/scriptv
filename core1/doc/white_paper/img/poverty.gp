set terminal pngcairo size 300,200 enhanced font 'Verdana,10'
set output "current_wealth_distribution.png"
set title "world wealth distribution (current)"
set xlabel "population (Billions)"
set ylabel "wealth (%)"
set ytics nomirror
set key left top
set xrange [0:10]
set yrange [0:100]
set y2range [0:100] # set y2range for the second y-axis
set y2tics 10 nomirror dt (5,5) lt 1 lw 2
plot 1+exp(5*(x-9)) axis x1y1 linecolor "black" lw 2 title "individual wealth", 10 axis x1y2 linecolor "black" dt (4,4) lw 1 title "poverty reference"

set title "world wealth distribution (target)"
set output "target_wealth_distribution.png"

plot 21+exp(5*(x-9))+1*(x-8) axis x1y1 linecolor "black" lw 2 title "individual wealth", 10 axis x1y2 linecolor "black" dt (4,4) lw 1 title "poverty reference"

