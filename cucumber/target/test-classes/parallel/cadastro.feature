# language: pt
@steps
Funcionalidade:   testar Cadastro

  Esquema do Cenário: Pesquisa no BING
    Dado que estou na página inicial do  BING
    Quando eu pesquiso no BING por "<termo de pesquisa>"
    Então eu vejo os resultados da pesquisa no  BING

    Exemplos:
      | termo de pesquisa |
      | Testes automatizados |
      | OpenAI            |
      | Sistemas distribuídos |
      | OpenAI            |
      | Testes automatizados |
      | Sistemas distribuídos |
      | OpenAI            |
      | Testes automatizados |
      | Sistemas distribuídos |



