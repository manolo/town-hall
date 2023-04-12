import { MessageInput } from "@hilla/react-components/MessageInput.js";
import { VirtualList } from "@hilla/react-components/VirtualList.js";
import Question from "Frontend/generated/com/example/application/data/Question.js";

import { TownHallEndpoint } from "Frontend/generated/endpoints.js";
import { useEffect, useState } from "react";

export default function HelloReactView() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    TownHallEndpoint.getQuestions().then(setQuestions);
  }, []);

  return (
    <div className="h-full flex flex-col">
      <section className="flex-grow">
        <VirtualList items={questions} className="p-m h-full box-border">
          {({ item }) => <span>{item.text}</span>}
        </VirtualList>
      </section>

      <section>
        <MessageInput
          i18n={{
            send: "Ask",
            message: "Type your question",
          }}
          onSubmit={async (ev) => {
            const question: Question = { text: ev.detail.value, score: 0 };
            await TownHallEndpoint.submitQuestion(question);
            setQuestions([...questions, question]);
          }}
        />
      </section>
    </div>
  );
}
