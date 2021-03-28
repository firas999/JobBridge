<?php

namespace App\Controller;

use App\Entity\Employee;
use App\Entity\Urlizer;
use App\Entity\User;
use App\Form\EmployeeType;
use App\Repository\EmployeeRepository;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\FileType;
use Symfony\Component\Security\Core\Encoder\EncoderFactoryInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


/**
 * @Route("/employer", )
 */

class EmployeeFrontController extends AbstractController
{

    private EncoderFactoryInterface $encoder;
    private UserPasswordEncoderInterface $pwdEncoder;
    public function __construct(EncoderFactoryInterface $encoder,UserPasswordEncoderInterface $enc)
    {
        $this->encoder = $encoder;
        $this->pwdEncoder = $enc;
    }
/**
* @Route("/p", name="employeefront_index")
*/
public function index(EmployeeRepository $employeeRepository): Response
{
return $this->render('employee_front/index.html.twig', [
'employees' => $employeeRepository->findAll(),
]);
}

/**
* @Route("/new", name="employeefront_new", methods={"GET","POST"})
*/
public function new(Request $request): Response
{
$employee = new Employee();
$salt = md5(microtime());
$form = $this->createForm(EmployeeType::class, $employee);
$form->handleRequest($request);
$encoder = $this->encoder->getEncoder(User::class);

if ($form->isSubmitted() && $form->isValid())
{ /** @var UploadedFile $uploadedFile */
$uploadedFile = $form['imageFile']->getData();
$destination = $this->getParameter('kernel.project_dir').'/public/uploads';
$originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
$newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
$uploadedFile->move(
$destination,
$newFilename
);
$employee->setImg($newFilename);



{
$entityManager = $this->getDoctrine()->getManager();
$encodedPassword=$encoder->encodePassword($employee->getMotdepasse(),$salt);
$employee->setMotdepasse($this->pwdEncoder->encodePassword($employee,$employee->getMotdepasse()));
$employee->setRoles(['ROLE_EMPLOYER']);
$entityManager->persist($employee);
$entityManager->flush();

return $this->redirectToRoute('employeefront_index');
}}

return $this->render('employee_front/new.html.twig', [
'employee' => $employee,
'form' => $form->createView(),
]);
}

/**
* @Route("/{id}", name="employeefront_show", methods={"GET"})
*/
public function show(Employee $employee): Response
{
return $this->render('employee_front/show.html.twig', [
'employee' => $employee,
]);
}

/**
* @Route("/{id}/edit", name="employeefront_edit", methods={"GET","POST"})
*/
public function edit(Request $request, Employee $employee): Response
{
$form = $this->createForm(EmployeeType::class, $employee);
$form->handleRequest($request);

if ($form->isSubmitted() && $form->isValid()) {
    { /** @var UploadedFile $uploadedFile */
    $uploadedFile = $form['imageFile']->getData();
    $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
    $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
    $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
    $uploadedFile->move(
        $destination,
        $newFilename
    );
    $employee->setImg($newFilename);
$this->getDoctrine()->getManager()->flush();

return $this->redirectToRoute('employeefront_index');
}}

return $this->render('employee_front/edit.html.twig', [
'employee' => $employee,
'form' => $form->createView(),
]);
}

/**
* @Route("/{id}", name="employeefront_delete", methods={"DELETE"})
*/
public function delete(Request $request, Employee $employee): Response
{
if ($this->isCsrfTokenValid('delete'.$employee->getId(), $request->request->get('_token'))) {
$entityManager = $this->getDoctrine()->getManager();
$entityManager->remove($employee);
$entityManager->flush();
}

return $this->redirectToRoute('employeefront_index');
}



}

