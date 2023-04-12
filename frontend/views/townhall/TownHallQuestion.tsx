import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { dateFormatter } from 'Frontend/utils/lang.js';
import styles from './TownHallQuestion.module.scss';

export type TownHallQuestionProps = Readonly<{
  item: Question;
}>;

export default function TownHallQuestion({ item }: TownHallQuestionProps) {
  return (
    <section className={styles.container}>
      <div className={styles.score}>{item.score}</div>
      <div className={styles.text}>{item.text}</div>
      <div className={styles.date}>{dateFormatter.format(new Date(item.created))}</div>
    </section>
  );
}
