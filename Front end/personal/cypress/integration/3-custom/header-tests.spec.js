beforeEach(() => {
    cy.visit("http://localhost:3000")
    cy.get('.username-input').type('Peter')
    cy.get('.password-input').type('123')
    cy.get('#loginButton').click()
    cy.url().should('include', '/menu')
    cy.visit("http://localhost:3000/menu")
})

it("Logging out sends the user back to the login screen", () => {
    cy.get('#logoutButton').click()
    cy.url().should('include', '/')
})

it("Logging out should clear the loggedInUser object in localstorage", () => {
    cy.get('#logoutButton').click().should(() => {
        expect(localStorage.getItem('loggedInUser')).to.be.null
    })
})

// it("While logged in, header should show user information", () => {
//     cy.get('.userInformation li:first').should('have.value', 'Name: Peter')
// })