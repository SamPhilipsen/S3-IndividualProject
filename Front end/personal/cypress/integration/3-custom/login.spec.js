beforeEach(() => {
    cy.visit("http://localhost:3000")
})

it("Succesfull login to website", () => {
    cy.get('.username-input')
    .type('Peter').should('have.value', 'Peter')
    cy.get('.password-input')
    .type('123').should('have.value', '123')
    cy.get('#loginButton').click()
    cy.url().should('include', '/menu')
})

it("Not succesfull login should show message at the top", () => {
    cy.get('#loginButton').click()
    cy.get('.network-error-text').should('be.visible')
})