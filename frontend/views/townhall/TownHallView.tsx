import { MessageInput } from '@hilla/react-components/MessageInput.js';
import { VirtualList } from '@hilla/react-components/VirtualList.js';
import cn from 'classnames';
import { useEffect, useState } from 'react';
import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { TownHallEndpoint } from 'Frontend/generated/endpoints.js';
import TownHallQuestion from 'Frontend/views/townhall/TownHallQuestion.js';
import styles from './TownHallView.module.css';

function sortQuestions(questions: Question[]) {
  // Sort the questions by priority
  return questions.sort((a, b) => b.priority - a.priority);
}

export default function HelloReactView() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    TownHallEndpoint.getQuestions().then(setQuestions);
  }, []);

  return (
    <div className="h-full flex flex-col">
      <section className="flex-grow">
        <VirtualList items={sortQuestions(questions)} className={cn('p-m', 'h-full', 'box-border', styles.questions)}>
          {({ item: question }) => (
            <TownHallQuestion
              item={question}
              onPriorityChange={async (priority) => {
                question.priority = priority;
                setQuestions([...questions]);
                await TownHallEndpoint.setPriority(question, priority);
              }}
            />
          )}
        </VirtualList>
      </section>

      <section>
        <MessageInput
          i18n={{
            send: 'Ask',
            message: 'Type your question',
          }}
          onSubmit={async (ev) => {
            const question: Question = {
              text: ev.detail.value,
              score: 0,
              created: new Date().toISOString(),
              userVoted: false,
              id: 0,
              priority: 0,
            };
            await TownHallEndpoint.submitQuestion(question);
            setQuestions([...questions, question]);
          }}
        />
      </section>
    </div>
  );
}
