import { Grid } from '@hilla/react-components/Grid.js';
import { GridColumn } from '@hilla/react-components/GridColumn.js';

import styles from './Sessions.module.css';

export default function SessionsView() {

  type Fruit = { name: string; quantity: number; symbol: string };
  const fruits = [
    { name: "apples", quantity: 5, symbol: "🍎" },
    { name: "oranges", quantity: 3, symbol: "🍊" },
    { name: "bananas", quantity: 9, symbol: "🍌" },
    { name: "grapes", quantity: 7, symbol: "🍇" },
    { name: "cherries", quantity: 10, symbol: "🍒" },
    { name: "pineapples", quantity: 2, symbol: "🍍" },
    { name: "peaches", quantity: 6, symbol: "🍑" },
    { name: "watermelons", quantity: 1, symbol: "🍉" },
    { name: "strawberries", quantity: 4, symbol: "🍓" },
    { name: "blueberries", quantity: 8, symbol: "🫐" },
  ];

  return (
    <div className="h-full flex flex-col">
      <h1>Sessions</h1>
      <Grid
        items={fruits}
      >
      <GridColumn path="name" />
      <GridColumn path="quantity" />
    </Grid>
    </div>
  );
}
